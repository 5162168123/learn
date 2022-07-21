package cn.meng.demo.register.server;

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

    private class Deamon implements Runnable{

        ServiceRegistry registry = ServiceRegistry.getInstance();

        @Override
        public void run() {

            Map<String, Map<String, ServiceInstance>> map = this.registry.getRegistry();
            while (true){
                System.out.println("服务存活检查，当前服务【"+map.size()+"】个");
                try {
                    SelfProtectionPolicy selfProtectionPolicy = SelfProtectionPolicy.getInstance();
                    if(selfProtectionPolicy.isEnable()){
                        Thread.sleep(CHECK_ALIVE_INTERVAL);
                        continue;
                    }


                    for(String serviceName:map.keySet()){
                        Map<String, ServiceInstance> serviceInstanceMap = map.get(serviceName);
                        for(ServiceInstance serviceInstance :serviceInstanceMap.values())
                            if(!serviceInstance.isAlive()){
                                registry.remove(serviceName,serviceInstance.getServiceInstanceId());
                                synchronized(SelfProtectionPolicy.class){
                                    selfProtectionPolicy = SelfProtectionPolicy.getInstance();
                                    selfProtectionPolicy.setExpectedHeartbeatRate( selfProtectionPolicy.getExpectedHeartbeatRate() - 2);
                                    selfProtectionPolicy.setExpectedHeartbeatThreshold(
                                            (long) (selfProtectionPolicy.getExpectedHeartbeatRate()*0.85));
                                }
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
