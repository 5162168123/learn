package cn.meng;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
public class HttpSender {

    /**
     * 发送注册请求
     * @param registerRequest
     * @return
     */
    public static RegisterResponse sender(RegisterRequest registerRequest){
        System.out.println("服务实例【"+registerRequest.getServiceInstanceId()+"】发送注册请求");
        return RegisterResponse.builder().code(ResponseEnum.SUCCESS.getCode()).message(ResponseEnum.SUCCESS.getMessage()).status(ResponseEnum
                .SUCCESS.getStatus()).build();
    }


    /**
     * 发送心跳线程
     * @param request
     * @return
     */
    public static HeartbeatResponse heartbeatSender(HeartbeatRequest request){
        System.out.println("实例【"+request.getServiceInstanceId()+"】发送心跳请求");
        return HeartbeatResponse.builder().code(ResponseEnum.SUCCESS.getCode()).message(ResponseEnum.SUCCESS.getMessage()).status(ResponseEnum
                .SUCCESS.getStatus()).build();
    }


    /**
     * 拉取注册表
     */
    public static Applications fetchFullRegistry(){
        Map<String, Map<String,ServiceInstance>> registry = new HashMap<>();
        Map<String,ServiceInstance> serviceInstanceMap = new HashMap<>();
        serviceInstanceMap.put("FINANCE-SERVICE-20200629", ServiceInstance.builder()
                .hostname("finance_service")
                .ip("192.168.0.1")
                .port(9000)
                .serviceInstanceId("FINANCE-SERVICE-20200629")
                .serviceName("FINANCE-SERVICE")
                .lease(null)
                .build());
        registry.put("FINANCE-SERVICE",serviceInstanceMap);
        System.out.println("拉取注册表" + registry);
        return new Applications(registry);
    }

    /**
     * 增量拉取服务注册表
     * @return
     */
    public static DeltaRegistry fetchDeltaRegistry() {
        LinkedList<CachedServiceRegistry.RecentlyChangedServiceInstance> recentlyChangedQueue =
                new LinkedList<>();

        ServiceInstance serviceInstance =ServiceInstance.builder()
        .hostname("order-service-01")
        .ip("192.168.31.288")
        .port(9000)
        .serviceInstanceId("ORDER-SERVICE-192.168.31.288:9000")
        .serviceName("ORDER-SERVICE").build();

        CachedServiceRegistry.RecentlyChangedServiceInstance recentlyChangedItem = new CachedServiceRegistry.RecentlyChangedServiceInstance(
                serviceInstance,
                System.currentTimeMillis(),
                "register");

        recentlyChangedQueue.offer(recentlyChangedItem);
        DeltaRegistry deltaRegistry = new DeltaRegistry(recentlyChangedQueue, 2L);
        System.out.println("拉取增量注册表：" + recentlyChangedQueue);

        return deltaRegistry;
    }
    public void cancel(String serviceName,String serviceInstanceId){
        System.out.println("服务"+serviceName+"下线");
    }

}
