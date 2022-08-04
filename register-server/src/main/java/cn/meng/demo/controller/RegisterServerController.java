package cn.meng.demo.controller;

import cn.meng.demo.register.server.*;

import java.util.LinkedList;
import java.util.Map;

public class RegisterServerController {

    /**
     * 服务注册表
     */
    private ServiceRegistry registry = ServiceRegistry.getInstance();
    /**
     * 服务注册表缓存
     */

    private ServiceRegistryCache serviceRegistryCache = ServiceRegistryCache.getInstance();

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
            //更新自我保护机制
            synchronized(SelfProtectionPolicy.class){
                SelfProtectionPolicy selfProtectionPolicy = SelfProtectionPolicy.getInstance();
                selfProtectionPolicy.setExpectedHeartbeatRate( selfProtectionPolicy.getExpectedHeartbeatRate()+2);
                selfProtectionPolicy.setExpectedHeartbeatThreshold(
                        (long) (selfProtectionPolicy.getExpectedHeartbeatRate()*0.85));
            }
            //过期注册表缓存
            serviceRegistryCache.invalidate();


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

            registry.writeLock();
            ServiceInstance serviceInstance = registry.getServiceInstance(request.getServiceName(),request.getServiceInstanceId());
            if(serviceInstance!=null){
                serviceInstance.renew();
                HeartbeatCounter.getInstance().increment();

            }
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
        return (Applications) serviceRegistryCache.get(ServiceRegistryCache.CacheKey.FULL_SERVICE_REGISTRY);
       /* try{
            registry.readLock();
            return new Applications(registry.getRegistry());
        }finally {
            registry.unReadLock();
        }*/

    }


    /**
     * 拉取增量注册表
     * @return
     */
    public DeltaRegistry fetchDeltaRegistry(){
        return (DeltaRegistry) serviceRegistryCache.get(ServiceRegistryCache.CacheKey.DELTA_SERVICE_REGISTRY);
      /*  try{
            registry.readLock();
            return registry.getDeltaRegistry();
        }finally {
            registry.unReadLock();
        }
*/
    }

        /**
     * 下线服务
     * @param serviceName
     * @param serviceInstanceId
     */
    public void cancel(String serviceName,String serviceInstanceId){
        registry.remove(serviceName,serviceInstanceId);
        //更新自我保护策略
        synchronized(SelfProtectionPolicy.class){
            SelfProtectionPolicy selfProtectionPolicy = SelfProtectionPolicy.getInstance();
            selfProtectionPolicy.setExpectedHeartbeatRate( selfProtectionPolicy.getExpectedHeartbeatRate()-2);
            selfProtectionPolicy.setExpectedHeartbeatThreshold(
                    (long) (selfProtectionPolicy.getExpectedHeartbeatRate()*0.85));
        }

        ServiceRegistryCache.getInstance().invalidate();
    }
}
