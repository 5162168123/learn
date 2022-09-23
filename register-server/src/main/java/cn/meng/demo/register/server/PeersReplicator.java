package cn.meng.demo.register.server;

/**
 * 集群同步组件
 */
public class PeersReplicator {

    private static final PeersReplicator instance = new PeersReplicator();
    private PeersReplicator(){

    }
    public static PeersReplicator getInstance(){
        return instance;
    }

    /**
     * 同步服务注册信息
     */
    public void replicateRegister(){

    }

    /***
     * 同步服务下线信息
     */
    public void replicateCancel(){

    }
}
