package cn.meng.demo.register.server.web;

import cn.meng.demo.register.server.cluster.PeersReplicator;
import cn.meng.demo.register.server.cluster.PeersReplicatorBatch;
import cn.meng.demo.register.server.core.*;
import cn.meng.demo.register.server.web.*;

import java.util.List;

public class RegisterServerController {

    /**
     * 服务注册表
     */
    private ServiceRegistry registry = ServiceRegistry.getInstance();
    /**
     * 集群同步组件
     */

    private PeersReplicator replicator = PeersReplicator.getInstance();
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
        //构造被注册的服务实例
        try{
            ServiceInstance serviceInstance = ServiceInstance.builder()
                    .hostname(registerRequest.getHostname())
                    .ip(registerRequest.getIp())
                    .port(registerRequest.getPort())
                    .serviceInstanceId(registerRequest.getServiceInstanceId())
                    .serviceName(registerRequest.getServiceName())
                    .lease(null)
                    .build();

            //调用注册方法写入注册表
            registry.register(serviceInstance);

            //返回给caller
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
            replicator.replicateRegister(registerRequest);

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
            replicator.replicateHeartBeat(request);
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
     * 同步batch数据
     * @param batch
     */
    public void replicateBatch(PeersReplicatorBatch batch) {
        for(AbstractRequest request : batch.getRequests()) {
            if(request.getType().equals(AbstractRequest.REGISTER_REQUEST)) {
                register((RegisterRequest) request);
            } else if(request.getType().equals(AbstractRequest.CANCEL_REQUEST)) {
                cancel((CancelRequest) request);
            } else if(request.getType().equals(AbstractRequest.HEARTBEAT_REQUEST)) {
                heartbeat((HeartbeatRequest) request);
            }
        }
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
     * @param request
     */
    public void cancel(CancelRequest request){
        String serviceName = request.getServiceName();
        String serviceInstanceId = request.getServiceInstanceId();
        registry.remove(serviceName,serviceInstanceId);
        //更新自我保护策略
        synchronized(SelfProtectionPolicy.class){
            SelfProtectionPolicy selfProtectionPolicy = SelfProtectionPolicy.getInstance();
            selfProtectionPolicy.setExpectedHeartbeatRate( selfProtectionPolicy.getExpectedHeartbeatRate()-2);
            selfProtectionPolicy.setExpectedHeartbeatThreshold(
                    (long) (selfProtectionPolicy.getExpectedHeartbeatRate()*0.85));
        }

        ServiceRegistryCache.getInstance().invalidate();
        replicator.replicateCancel(request);
    }
}
