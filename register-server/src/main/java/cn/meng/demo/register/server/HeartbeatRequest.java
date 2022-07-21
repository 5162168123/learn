package cn.meng.demo.register.server;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HeartbeatRequest {

    private String ServiceInstanceId;
    private String serviceName;

}
