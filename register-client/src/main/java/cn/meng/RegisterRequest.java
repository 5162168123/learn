package cn.meng;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class RegisterRequest {

    private String ip;
    private String hostname;
    private int port;
    private String ServiceInstanceId;
    private String ServiceName;

}
