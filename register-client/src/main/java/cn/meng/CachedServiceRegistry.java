package cn.meng;

import javafx.application.Application;
import lombok.Builder;
import lombok.ToString;

import javax.net.ssl.HttpsURLConnection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CachedServiceRegistry {

    /**
     * 注册表拉取间隔
     */
    private static final Long SERVICE_REGISTRY_FETCH_INTERVAL = 30 * 1000L;

    /**
     * 缓存完整注册表信息
     */
    private AtomicStampedReference<Applications> applications;

    /**
     * 代表了当前的本地缓存的服务注册表的一个版本号
     */
    private AtomicLong applicationsVersion = new AtomicLong(0L);
    /**
     * 本地缓存注册表的读写锁
     */
    private ReentrantReadWriteLock applicationsLock = new ReentrantReadWriteLock();

    private ReentrantReadWriteLock.WriteLock writeLock = applicationsLock.writeLock();
    private ReentrantReadWriteLock.ReadLock readLock = applicationsLock.readLock();

    private RegisterClient registerClient;

    private FetchFullRegistryWorker fetchFullRegistryWorker;
    /**
     * 定时拉取增量注册表信息线程
     */
    private FetchDeltaRegistryWorker fetchDeltaRegistryWorker;

    public CachedServiceRegistry(RegisterClient registerClient){
        this.fetchFullRegistryWorker = new FetchFullRegistryWorker();
        this.fetchDeltaRegistryWorker = new FetchDeltaRegistryWorker();
        this.registerClient = registerClient;
        applications = new AtomicStampedReference<>(new Applications(),0);
    }

    public void initialize(){

        fetchFullRegistryWorker.start();
        fetchDeltaRegistryWorker.start();
    }

    public void destroy(){
        this.fetchDeltaRegistryWorker.interrupt();
    }

    /**
     * 全量拉取注册表的后台线程
     * @author zhonghuashishan
     *
     */
    private class FetchFullRegistryWorker extends Thread {

        @Override
        public void run() {
            fetchFullRegistry();
        }

    }

    /**
     * 拉取全量注册表到本地
     */
    private void fetchFullRegistry() {
        while(true) {
        Long expectedVersion = applicationsVersion.get(); // version = 0
        Applications fetchedApplications = HttpSender.fetchFullRegistry();

        if(applicationsVersion.compareAndSet(expectedVersion, expectedVersion + 1)) { // version = 1

                Applications expectedApplications = applications.getReference();
                int expectedStamp = applications.getStamp();
                if(applications.compareAndSet(expectedApplications, fetchedApplications,
                        expectedStamp, expectedStamp + 1)) {
                    break;
                }
            }
        }
    }
    /**
     * 增量拉取注册表的后台线程
     * @author zhonghuashishan
     *
     */
    private class FetchDeltaRegistryWorker extends Thread {

        @Override
        public void run() {
            while(registerClient.isRunning()) {
                try {
                    Thread.sleep(SERVICE_REGISTRY_FETCH_INTERVAL);
                    DeltaRegistry  deltaRegistry = HttpSender.fetchDeltaRegistry();
                    // 一类是注册，一类是删除
                    // 如果是注册的话，就判断一下这个服务实例是否在这个本地缓存的注册表中
                    // 如果不在的话，就放到本地缓存注册表里去
                    // 如果是删除的话，就看一下，如果服务实例存在，就给删除了

                    // 我们这里其实是要大量的修改本地缓存的注册表，所以此处需要加锁

                        mergeDeltaRegistry(deltaRegistry);

                    // 再检查一下，跟服务端的注册表的服务实例的数量相比，是否是一致的
                    // 封装一下增量注册表的对象，也就是拉取增量注册表的时候，一方面是返回那个数据
                    // 另外一方面，是要那个对应的register-server端的服务实例的数量
                    reconcileRegistry(deltaRegistry);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void mergeDeltaRegistry(DeltaRegistry deltaRegistry) {
            try {
                writeLock.lock();
                Map<String, Map<String, ServiceInstance>> registry =
                        applications.getReference().getRegistry();

                LinkedList<RecentlyChangedServiceInstance> recentlyChangedQueue =
                        deltaRegistry.getRecentlyChangedQueue();

                for(RecentlyChangedServiceInstance recentlyChangedItem : recentlyChangedQueue) {
                    String serviceName = recentlyChangedItem.serviceInstance.getServiceName();
                    String serviceInstanceId = recentlyChangedItem.serviceInstance.getServiceInstanceId();

                    // 如果是注册操作的话
                    if(ServiceInstanceOperation.REGISTER.equals(
                            recentlyChangedItem.serviceInstanceOperation)) {
                        Map<String, ServiceInstance> serviceInstanceMap = registry.get(serviceName);
                        if(serviceInstanceMap == null) {
                            serviceInstanceMap = new HashMap<String, ServiceInstance>();
                            registry.put(serviceName, serviceInstanceMap);
                        }

                        ServiceInstance serviceInstance = serviceInstanceMap.get(serviceInstanceId);
                        if(serviceInstance == null) {
                            serviceInstanceMap.put(serviceInstanceId,
                                    recentlyChangedItem.serviceInstance);
                        }
                    }

                    // 如果是删除操作的话
                    else if(ServiceInstanceOperation.REMOVE.equals(
                            recentlyChangedItem.serviceInstanceOperation)) {
                        Map<String, ServiceInstance> serviceInstanceMap = registry.get(serviceName);
                        if(serviceInstanceMap != null) {
                            serviceInstanceMap.remove(serviceInstanceId);
                        }
                    }
                }
            }
            finally {
                writeLock.unlock();
            }
        }

        private void reconcileRegistry(DeltaRegistry  deltaRegistry){
            Long serverSideTotalCount = deltaRegistry.getServiceInstanceTotalCount();

            Long clientSideTotalCount = 0L;
            for(Map<String, ServiceInstance> serviceInstanceMap : applications.getReference().getRegistry().values()) {
                clientSideTotalCount += serviceInstanceMap.size();
            }

            if(serverSideTotalCount != clientSideTotalCount) {
                // 拉取全量注册表
                Applications fetchedApplications = HttpSender.fetchFullRegistry();
                while(true) {
                    Applications expectedApplications = applications.getReference();
                    int expectedStamp = applications.getStamp();
                    if(applications.compareAndSet(expectedApplications, fetchedApplications,expectedStamp,expectedStamp+1)) {
                        break;
                    }
                }
            }
        }

        /**
         * 获取服务注册表
         * @return
         */
        public Map<String, Map<String, ServiceInstance>> getRegistry() {
            try{
               readLock.lock();
                return applications.getReference().getRegistry();
            }finally {
                readLock.unlock();
            }

        }
        /**
         * 服务实例操作
         * @author zhonghuashishan
         *
         */
        class ServiceInstanceOperation {

            /**
             * 注册
             */
            public static final String REGISTER = "register";
            /**
             * 删除
             */
            public static final String REMOVE = "REMOVE";

        }
    }
    /**
     * 最近变更的实例信息
     * @author zhonghuashishan
     *
     */
    @ToString
    @Builder
    static class RecentlyChangedServiceInstance {

        /**
         * 服务实例
         */
        ServiceInstance serviceInstance;
        /**
         * 发生变更的时间戳
         */
        Long changedTimestamp;
        /**
         * 变更操作
         */
        String serviceInstanceOperation;

    }
}
