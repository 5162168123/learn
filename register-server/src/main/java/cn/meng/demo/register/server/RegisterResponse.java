package cn.meng.demo.register.server;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RegisterResponse {

    private int code;
    private String message;
    private String status;
}
