package cn.meng.demo.register.server.web;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class HeartbeatResponse {


    private int code;
    private String message;
    private String status;
}
