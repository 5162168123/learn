package cn.meng.demo.register.server;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 注册表实例
 */
@ToString
public class ServiceRegistry {
    /*
    检查最近变更队列间隔
     */
    public static final Long RECENTLY_CHANGED_ITEM_CHECK_INTERVAL=3*1000L;
    /*
    最近变更信息存储阈值
     */
    public static final Long RECENTLY_CHANGED_ITEM_EXPIRED = 3 * 60 * 1000L;
    private static ServiceRegistry instance = new ServiceRegistry();

    private ServiceRegistry(){
        RecentlyChangedQueueMonitor recentlyChangedQueueMonitor = new RecentlyChangedQueueMonitor();
        recentlyChangedQueueMonitor.setDaemon(true);
        recentlyChangedQueueMonitor.start();
    }

    /*
    最近更新的服务实例列队
     */
    private LinkedList<RecentlyChangedServiceInstance> recentlyChangedQueue = new LinkedList<>();

    /*
    注册表
     */
    private Map<String,Map<String,ServiceInstance>> registry = new HashMap<>();


    /**
     * 注册服务
     * @param serviceInstance 服务实例
     */
    public synchronized void register(ServiceInstance serviceInstance){
        //服务实例放入注册表中
        Map<String,ServiceInstance> serviceInstanceMap = registry.get(serviceInstance.getServiceName());
        if(serviceInstanceMap == null){
            serviceInstanceMap = new HashMap<>();
            registry.put(serviceInstance.getServiceName(),serviceInstanceMap);
        }
        serviceInstanceMap.put(serviceInstance.getServiceInstanceId(),serviceInstance);

        //服务实例放入变更列队
        RecentlyChangedServiceInstance build = RecentlyChangedServiceInstance.builder().changedTimestamp(System.currentTimeMillis())
                .serviceInstance(serviceInstance)
                .serviceInstanceOperation(RecentlyChangedOperation.REGISTRY)
                .build();
        recentlyChangedQueue.offer(build);
        System.out.println("服务实例【"+serviceInstance +"】注册成功");
        System.out.println("注册表【"+registry+"】");
    }
    public static ServiceRegistry getInstance(){
        return instance;
    }

    /**
     * 获取服务实例
     * @param serviceName 服务名称
     * @param ServiceInstanceId 服务id
     * @return
     */
    public synchronized ServiceInstance getServiceInstance(String serviceName,String ServiceInstanceId){
        Map<String, ServiceInstance> serviceInstanceMap = registry.get(serviceName);
        return serviceInstanceMap.get(ServiceInstanceId);
    }


    /**
     * 返回全量注册表
     * @return
     */
    public synchronized Map<String,Map<String,ServiceInstance>> getRegistry() {
        return registry;
    }

    /**
     * 获取最近有变化的注册表
     * @return
     */
    public synchronized DeltaRegistry getDeltaRegistry() {
        Long totalCount = 0L;
        for(Map<String, ServiceInstance> serviceInstanceMap : registry.values()) {
            totalCount += serviceInstanceMap.size();
        }

        DeltaRegistry deltaRegistry = new DeltaRegistry(
                recentlyChangedQueue, totalCount);

        return deltaRegistry;
    }
    /**
     * 删除服务实例
     * @param serviceName
     * @param serviceInstanceId
     */
    public synchronized void remove(String serviceName,String serviceInstanceId) {
        Map<String, ServiceInstance> serviceInstanceMap = registry.get(serviceName);
        System.out.println("服务实例【"+serviceInstanceId+"】摘除");
        ServiceInstance serviceInstance = serviceInstanceMap.get(serviceInstanceId);
        //服务实例放入变更列队
        RecentlyChangedServiceInstance build = RecentlyChangedServiceInstance.builder().changedTimestamp(System.currentTimeMillis())
                .serviceInstance(serviceInstance)
                .serviceInstanceOperation(RecentlyChangedOperation.REMOVE)
                .build();
        recentlyChangedQueue.offer(build);
        serviceInstanceMap.remove(serviceInstanceId);
        if(serviceInstanceMap.size() == 0){
            registry.remove(serviceName);
        }
    }

    /**
     * 最近更新服务信息
     */

    @Builder
    public class RecentlyChangedServiceInstance{
        ServiceInstance serviceInstance;
        Long changedTimestamp;
        String serviceInstanceOperation;
    }

    /**
     * 服务更新操作
     */
    class RecentlyChangedOperation{
        public static final String REGISTRY = "registry";
        public static final String REMOVE = "remove";
    }

    class RecentlyChangedQueueMonitor extends Thread{
        @Override
        public void run() {
            while(true){
                try {
                    synchronized(instance){
                        RecentlyChangedServiceInstance recentlyChangedServiceInstance;
                        long l = System.currentTimeMillis();
                        while((recentlyChangedServiceInstance = recentlyChangedQueue.peek())!=null){
                            if(l - recentlyChangedServiceInstance.changedTimestamp >
                            RECENTLY_CHANGED_ITEM_EXPIRED){
                                recentlyChangedQueue.poll();
                            }
                        }
                    }

                    Thread.sleep(RECENTLY_CHANGED_ITEM_CHECK_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
