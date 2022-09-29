package cn.meng.demo.register.server.web;

import lombok.Builder;
import lombok.Data;

/**
 * 服务下线请求
 */
@Data
@Builder
public class CancelRequest extends AbstractRequest{
    /**
     * 请求类型
     */
    private Integer type = CANCEL_REQUEST;

    public Integer getType(){
        return type;
    }
    public String getServiceInstanceId(){
        return super.serviceInstanceId;
    }
    public String getServiceName(){
        return super.serviceName;
    }
}
