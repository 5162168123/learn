package cn.meng.demo.controller;

import cn.meng.demo.register.server.*;

import java.util.LinkedList;
import java.util.Map;

public class RegisterServerController {

    private ServiceRegistry registry = ServiceRegistry.getInstance();


    /**
     * 服务注册
     * @param registerRequest
     * @return
     */
    public RegisterResponse register(RegisterRequest registerRequest){
        RegisterResponse response;
        try{
            ServiceInstance serviceInstance = ServiceInstance.builder()
                    .hostname(registerRequest.getHostname())
                    .ip(registerRequest.getIp())
                    .port(registerRequest.getPort())
                    .serviceInstanceId(registerRequest.getServiceInstanceId())
                    .serviceName(registerRequest.getServiceName())
                    .lease(null)
                    .build();


            registry.register(serviceInstance);

            response = RegisterResponse.builder()
                    .code(ResponseEnum.SUCCESS.getCode())
                    .message(ResponseEnum.SUCCESS.getMessage())
                    .status(ResponseEnum.SUCCESS.getStatus())
                    .build();
            synchronized(SelfProtectionPolicy.class){
                SelfProtectionPolicy selfProtectionPolicy = SelfProtectionPolicy.getInstance();
                selfProtectionPolicy.setExpectedHeartbeatRate( selfProtectionPolicy.getExpectedHeartbeatRate()+2);
                selfProtectionPolicy.setExpectedHeartbeatThreshold(
                        (long) (selfProtectionPolicy.getExpectedHeartbeatRate()*0.85));
            }


            }catch (Exception e){
                response = RegisterResponse.builder()
                        .code(ResponseEnum.FAILURE.getCode())
                        .message(ResponseEnum.FAILURE.getMessage())
                        .status(ResponseEnum.FAILURE.getStatus())
                        .build();
            }
            return response;
    }


    /**
     * 心跳服务
     * @param request
     * @return
     */
    public HeartbeatResponse heartbeat(HeartbeatRequest request){
        HeartbeatResponse response;
        try{
            ServiceInstance serviceInstance = registry.getServiceInstance(request.getServiceName(),request.getServiceInstanceId());
            serviceInstance.renew();
            HeartbeatCounter.getInstance().increment();
            response = HeartbeatResponse.builder()
                    .code(ResponseEnum.SUCCESS.getCode())
                    .message(ResponseEnum.SUCCESS.getMessage())
                    .status(ResponseEnum.SUCCESS.getStatus())
                    .build();

        }catch (Exception e){
            e.printStackTrace();
            response = HeartbeatResponse.builder()
                    .code(ResponseEnum.FAILURE.getCode())
                    .message(ResponseEnum.FAILURE.getMessage())
                    .status(ResponseEnum.FAILURE.getStatus())
                    .build();
        }
        return response;
    }


    /**
     * 拉取全量注册表
     * @return
     */
    public Applications fetchFullRegistry() {
        return new Applications(registry.getRegistry());
    }


    /**
     * 拉取增量注册表
     * @return
     */
    public DeltaRegistry fetchDeltaRegistry(){
        return registry.getDeltaRegistry();
    }

        /**
     * 下线服务
     * @param serviceName
     * @param serviceInstanceId
     */
    public void cancel(String serviceName,String serviceInstanceId){
        registry.remove(serviceName,serviceInstanceId);
        synchronized(SelfProtectionPolicy.class){
            SelfProtectionPolicy selfProtectionPolicy = SelfProtectionPolicy.getInstance();
            selfProtectionPolicy.setExpectedHeartbeatRate( selfProtectionPolicy.getExpectedHeartbeatRate()-2);
            selfProtectionPolicy.setExpectedHeartbeatThreshold(
                    (long) (selfProtectionPolicy.getExpectedHeartbeatRate()*0.85));
        }
    }
}
