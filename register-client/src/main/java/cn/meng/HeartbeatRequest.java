package cn.meng;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HeartbeatRequest {

    private String ServiceInstanceId;
    private String ServiceName;

}
