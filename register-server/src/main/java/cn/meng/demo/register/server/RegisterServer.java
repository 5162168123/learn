package cn.meng.demo.register.server;

import cn.meng.demo.register.server.web.RegisterServerController;
import cn.meng.demo.register.server.core.ServiceAliveMonitor;
import cn.meng.demo.register.server.web.HeartbeatRequest;
import cn.meng.demo.register.server.web.RegisterRequest;

import java.util.UUID;

public class RegisterServer {
    public static void main(String[] args) {
        RegisterServerController registerServerController = new RegisterServerController();
        String id = UUID.randomUUID().toString().replace("-","");
        RegisterRequest request = RegisterRequest.builder()
                .serviceName("test")
                .serviceInstanceId(id)
                .ip("xx.xx.x..x")
                .hostname("xxxxxx")
                .port(123)
                .build();
        registerServerController.register(request);

        HeartbeatRequest heartbeatRequest = HeartbeatRequest.builder()
                .serviceInstanceId(id)
                .serviceName("test")
                .build();
        registerServerController.heartbeat(heartbeatRequest);

        ServiceAliveMonitor serviceAliveMonitor = new ServiceAliveMonitor();
        serviceAliveMonitor.start();

        while(Thread.activeCount() >= 2)
            Thread.yield();
    }
}
