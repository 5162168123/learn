package cn.meng.demo.register.server;

import cn.meng.demo.controller.RegisterServerController;

import java.util.UUID;

public class RegisterServer {
    public static void main(String[] args) {
        RegisterServerController registerServerController = new RegisterServerController();
        String id = UUID.randomUUID().toString().replace("-","");
        RegisterRequest request = RegisterRequest.builder()
                .hostname("xxxxxx")
                .ip("xx.xx.x..x")
                .port(123)
                .ServiceInstanceId(id)
                .ServiceName("test")
                .build();
        registerServerController.register(request);

        HeartbeatRequest heartbeatRequest = HeartbeatRequest.builder()
                .ServiceInstanceId(id)
                .serviceName("test")
                .build();
        registerServerController.heartbeat(heartbeatRequest);

        ServiceAliveMonitor serviceAliveMonitor = new ServiceAliveMonitor();
        serviceAliveMonitor.start();

        while(Thread.activeCount() >= 2)
            Thread.yield();
    }
}
