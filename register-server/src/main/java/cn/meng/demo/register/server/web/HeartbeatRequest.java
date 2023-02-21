package cn.meng.demo.register.server.web;

import lombok.Builder;
import lombok.Data;

@Data
public class HeartbeatRequest extends AbstractRequest{

    /**
     * 请求类型
     */
    private Integer type = HEARTBEAT_REQUEST;
    public Integer getType(){
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
