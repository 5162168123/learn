package cn.meng.demo.register.server.core;

import java.util.concurrent.atomic.AtomicLong;

public class HeartbeatCounter {
    private static HeartbeatCounter instance = new HeartbeatCounter();

    //最近一分钟时间戳
    private long latestMinuteTimestamp = System.currentTimeMillis();

    //最近一分钟心跳次数
    private AtomicLong latestMinuteHeartbeatCount = new AtomicLong(0L);
    private HeartbeatCounter(){
        Thread daemon = new Thread(this::daemon);
        daemon.setDaemon(true);
        daemon.start();
    }

    public static HeartbeatCounter getInstance(){
        return instance;
    }


    /**
     * 增加最近一分钟心跳次数
     */
    public /*synchronized*/ void increment(){

        latestMinuteHeartbeatCount.incrementAndGet();
    }

    /**
     * 获取最近一分钟心跳次数
     * @return
     */
    public /*synchronized*/ long getLatestMinuteHeartbeatCount(){
        return latestMinuteHeartbeatCount.get();
    }

    private void daemon(){
        while(true){
            try{
                if(System.currentTimeMillis() -  latestMinuteTimestamp > 60 * 1000){
                    this.latestMinuteTimestamp = System.currentTimeMillis();
                    Long expectedValue = latestMinuteHeartbeatCount.get();
                    while (true){
                        if(latestMinuteHeartbeatCount.compareAndSet(expectedValue,0L)){
                           break;
                        }

                    }

                    this.latestMinuteHeartbeatCount.set(0L);
                }

//                System.out.println("时间戳未超过1分钟");
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

}
