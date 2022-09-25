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
    private int type = CANCEL_REQUEST;

    public int getType(){
        return type;
    }
    public String getServiceInstanceId(){
        return super.serviceInstanceId;
    }
    public String getServiceName(){
        return super.serviceName;
    }
}
