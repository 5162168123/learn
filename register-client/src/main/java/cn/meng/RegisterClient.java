package cn.meng;

import java.util.UUID;

public class RegisterClient {
    private String serviceInstanceId ;
//    心跳线程
    private Thread heartbeatWorkerThread;

//    注册服务线程
    private Thread registerClientWorkThread;
//    缓存注册表
    private CachedServiceRegistry registry;
    private volatile Boolean isRunning = true;

    public RegisterClient(){
        serviceInstanceId = UUID.randomUUID().toString().replace("-","");
        this.heartbeatWorkerThread = new Thread(new HeartbeatWorker(serviceInstanceId)) ;
        this.registry = new CachedServiceRegistry(this);
        registerClientWorkThread = new Thread(new RegisterClientWorker(serviceInstanceId));
    }
    public void start(){
        try{
            registerClientWorkThread.start();
            registerClientWorkThread.join();
            heartbeatWorkerThread.start();
            registry.initialize();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    /**
     * 关闭方法
     */
    public void shutdown(){
        this.isRunning = false;
//      准备推出registerClient
        System.out.println("准备退出registerClient");
        this.heartbeatWorkerThread.interrupt();
        System.out.println();
    }


    /**
     * 内部心跳检测类
     */
    private class HeartbeatWorker implements Runnable{
        private  String serviceInstanceId;

        public HeartbeatWorker(String serviceInstanceId){
            this.serviceInstanceId = serviceInstanceId;
        }

        @Override
        public void run() {
            HeartbeatRequest request = HeartbeatRequest.builder().ServiceInstanceId(serviceInstanceId)
                    .ServiceName("test").build();
            HeartbeatResponse response;
            while (isRunning){

                response = HttpSender.heartbeatSender(request);
                System.out.println("心跳请求结果"+response.getStatus()+"................");
                try {
                    Thread.sleep(30*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("心跳线程退出");
        }

    }

    /**
     * 注册服务类
     */
    private class RegisterClientWorker implements Runnable {

        String serviceInstanceId;
        public RegisterClientWorker(String serviceInstanceId){
            this.serviceInstanceId = serviceInstanceId;
        }


        @Override
        public void run() {

            System.out.println("注册请求中");
            RegisterRequest registerRequest = RegisterRequest.builder().hostname("xxx.xxx")
                    .ip("xx.xx.xx.xx")
                    .port(12345)
                    .ServiceInstanceId(serviceInstanceId)
                    .ServiceName("test")
                    .build();
            RegisterResponse sender = HttpSender.sender(registerRequest);
            if(sender.getStatus().equals(ResponseEnum.SUCCESS.getStatus())){
                System.out.println("注册的结果为："+ sender.getStatus()+".....");
            }else{
                throw new RuntimeException("服务注册失败");
            }

        }
    }

    public boolean isRunning(){
        return isRunning;
    }

}
