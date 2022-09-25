package cn.meng.demo.register.server.web;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RegisterRequest extends AbstractRequest{
    /**
     * 请求类型
     */
    private int type = REGISTER_REQUEST;
    private String ip;
    private String hostname;
    private int port;

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
    public RegisterRequest(String serviceName, String serviceInstanceId, String ip, String hostname, int port) {
        super(serviceName, serviceInstanceId);
        this.ip = ip;
        this.hostname = hostname;
        this.port = port;
    }
}
