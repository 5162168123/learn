package cn.meng.demo.register.server.web;

import lombok.Data;

@Data
public abstract class AbstractRequest {
    public static final Integer REGISTER_REQUEST = 1;
    public static final Integer CANCEL_REQUEST = 2;
    public static final Integer HEARTBEAT_REQUEST = 3;

    protected String serviceName;
    protected String serviceInstanceId;
    protected Integer type;
    public AbstractRequest(){

    }

    public AbstractRequest(String serviceName, String serviceInstanceId) {
        this.serviceName = serviceName;
        this.serviceInstanceId = serviceInstanceId;
    }

}
