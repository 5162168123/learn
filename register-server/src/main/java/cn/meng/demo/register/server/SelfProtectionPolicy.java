package cn.meng.demo.register.server;

import lombok.Data;


@Data
public class SelfProtectionPolicy {

    private static SelfProtectionPolicy instance = new SelfProtectionPolicy();

    private HeartbeatCounter heartbeatMeasuredRate = HeartbeatCounter.getInstance();
    private SelfProtectionPolicy(){

    }
    /**
     * 期望收到的心跳次数
     */
    private long expectedHeartbeatRate = 0L;

    /**
     * 自我保护阈值，期望心跳次数*0.85
     */
    private long expectedHeartbeatThreshold = 0L;

    public static SelfProtectionPolicy getInstance(){
        return instance;
    }


    /**
     * 是否需要开启自我保护机制
     * @return true 是 false  不需要
     */
    public boolean isEnable() {
        long latestMinuteHeartbeatRate = heartbeatMeasuredRate.getLatestMinuteHeartbeatCount();
        if(latestMinuteHeartbeatRate < expectedHeartbeatThreshold){
            System.out.println("【自我保护机制开启】，当前心跳次数="+latestMinuteHeartbeatRate+",期望的心跳次数="+expectedHeartbeatThreshold);
            return true;
        }

        System.out.println("【自我保护机制未开启】，当前心跳次数="+latestMinuteHeartbeatRate+",期望的心跳次数="+expectedHeartbeatThreshold);

        return false;
    }
}
