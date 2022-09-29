package cn.meng.demo.register.server.cluster;

import java.util.ArrayList;
import java.util.List;

public class RegisterServerClusterConfiguration {
    private static List<String> peers = new ArrayList<>();

    static{
        //读取配置文件获取集群机器信息
    }

    public static List<String> getPeers(){
        return peers;
    }
}
