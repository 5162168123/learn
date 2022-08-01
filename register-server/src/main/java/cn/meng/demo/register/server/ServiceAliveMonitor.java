package cn.meng.demo.register.server;

import java.util.LinkedList;
import java.util.Map;


/**
 * 服务存活检查线程
 */
public class ServiceAliveMonitor {

    private static final long CHECK_ALIVE_INTERVAL = 60 * 1000L;
    private Deamon deamon;

    public ServiceAliveMonitor(){
        deamon = new Deamon();
    }
    public void start(){
        Thread thread = new Thread(deamon);
        thread.setName("ServiceAliveMonitor");
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * 存活检测类
     */
    private class Deamon implements Runnable{

        ServiceRegistry registry = ServiceRegistry.getInstance();

        @Override
        public void run() {

            Map<String, Map<String, ServiceInstance>> map ;
            LinkedList<ServiceInstance> serviceInstanceToBeRemoved = new LinkedList();
            SelfProtectionPolicy selfProtectionPolicy;
            while (true){


                try {
                    //判断自我保护机制是否应该开启
                    selfProtectionPolicy = SelfProtectionPolicy.getInstance();
                    if(selfProtectionPolicy.isEnable()){
                        Thread.sleep(CHECK_ALIVE_INTERVAL);
                        continue;
                    }

                    //获取全量注册表并检查服务存货状态
                    try{
                        registry.readLock();
                        map = this.registry.getRegistry();
                        System.out.println("服务存活检查，当前服务【"+map.size()+"】个");
                        for(String serviceName:map.keySet()){
                            Map<String, ServiceInstance> serviceInstanceMap = map.get(serviceName);
                            for(ServiceInstance serviceInstance :serviceInstanceMap.values())
                                if(!serviceInstance.isAlive()){
                                    serviceInstanceToBeRemoved.add(serviceInstance);
                                }

                        }
                    }finally {
                        registry.unReadLock();
                    }


                    for (ServiceInstance serviceInstance : serviceInstanceToBeRemoved) {

                        registry.remove(serviceInstance.getServiceName(),serviceInstance.getServiceInstanceId());
                        synchronized(SelfProtectionPolicy.class){
                            selfProtectionPolicy = SelfProtectionPolicy.getInstance();
                            selfProtectionPolicy.setExpectedHeartbeatRate( selfProtectionPolicy.getExpectedHeartbeatRate() - 2);
                            selfProtectionPolicy.setExpectedHeartbeatThreshold(
                                    (long) (selfProtectionPolicy.getExpectedHeartbeatRate()*0.85));
                        }

                    }





                    Thread.sleep(CHECK_ALIVE_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
