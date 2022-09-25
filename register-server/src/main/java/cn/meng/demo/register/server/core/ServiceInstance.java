package cn.meng.demo.register.server.core;

import lombok.*;

@Data
@Builder
@ToString
/**
 * 服务实例
 */
public class ServiceInstance {
    private static final long NOT_ALIVE_PERIOD =90 * 1000L;
    private Lease lease ;
    private String ip;
    private String hostname;
    private String serviceName;
    private int port;


    private String serviceInstanceId;


    public ServiceInstance(Lease lease,String ip, String hostname, String serviceName, int port,  String serviceInstanceId) {
        this.lease = new Lease();
        this.ip = ip;
        this.hostname = hostname;
        this.serviceName = serviceName;
        this.port = port;

        this.serviceInstanceId = serviceInstanceId;
    }
/* public ServiceInstance(String ip, String hostname,String serviceName, int port,Lease lease, String serviceInstanceId) {
        this.ip = ip;
        this.hostname = hostname;
        this.serviceName = serviceName;
        this.lease = new Lease();
        this.port = port;
        this.serviceInstanceId = serviceInstanceId;
    }*/

    public void renew(){

        this.lease.renew();
        System.out.println(serviceInstanceId+"续约成功");
    }

    public boolean isAlive() {

        return this.lease.isAlive();
    }


    @Data
    @ToString
    public
    class Lease {
        private volatile long latestHeartbeatTime = System.currentTimeMillis();

        public void renew(){
            this.latestHeartbeatTime = System.currentTimeMillis();
            System.out.println("服务实例【"+serviceInstanceId+"】续约");
        }

        public boolean isAlive() {
            long l = System.currentTimeMillis();
            if(l - latestHeartbeatTime > NOT_ALIVE_PERIOD){
                System.out.println("服务实例【"+ serviceInstanceId+"】不再存活");
                return false;
            }
            System.out.println("服务实例【"+ serviceInstanceId+"】保持存活");

            return true;
        }
    }
}
