package cn.meng.demo.register.server.web;


public enum ResponseEnum {

    SUCCESS(200,"success","success"),
    FAILURE(400,"failure","failure");

    ResponseEnum(int code, String message, String status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    private int code;
    private String message;
    private String status;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
