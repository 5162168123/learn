package cn.meng.dfs.datanode.server;

import java.util.concurrent.CountDownLatch;

/**
 * 负责跟一组namenode通信
 */
public class NameNodeGroupOfferService {
    NameNodeServiceActor activeNameNode;
    NameNodeServiceActor standbyNameNode;

    public NameNodeGroupOfferService(){
        this.activeNameNode = new NameNodeServiceActor();
        this.standbyNameNode = new NameNodeServiceActor();
    }
    public void start() {
        register();
    }

    private void register() {
        CountDownLatch latch = new CountDownLatch(2);

        try {
            activeNameNode.register(latch);
            standbyNameNode.register(latch);
            latch.await();
            System.out.println("注册万了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
