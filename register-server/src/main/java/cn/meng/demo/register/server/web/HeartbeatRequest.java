package cn.meng.demo.register.server.web;

import lombok.Builder;
import lombok.Data;

@Data
public class HeartbeatRequest extends AbstractRequest{

    /**
     * 请求类型
     */
    private int type = HEARTBEAT_REQUEST;
    public int getType(){
        return type;
    }
    public String getServiceInstanceId(){
        return super.serviceInstanceId;
    }
    public String getServiceName(){
        return super.serviceName;
    }
    @Builder
    public HeartbeatRequest(String serviceInstanceId,String serviceName) {
        super(serviceName, serviceInstanceId);
    }
}
