package cn.meng.dfs.datanode.server;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * 负责跟一组namenode通信
 */
public class NameNodeGroupOfferService {

    /*
    负责与主节点通信
     */
    NameNodeServiceActor activeNameNode;
    /**
     * 负责与备节点通信
     */
    NameNodeServiceActor standbyNameNode;

    CopyOnWriteArrayList<NameNodeServiceActor> serviceActors;
    public NameNodeGroupOfferService(){
        this.activeNameNode = new NameNodeServiceActor();
        this.standbyNameNode = new NameNodeServiceActor();
        serviceActors = new CopyOnWriteArrayList<>();
        serviceActors.add(activeNameNode);
        serviceActors.add(standbyNameNode);
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

    public void shutdown(NameNodeServiceActor nameNodeServiceActor){
        serviceActors.remove(nameNodeServiceActor);
    }

    public void iteratorService(){
        Iterator<NameNodeServiceActor> iterator = serviceActors.iterator();
        while (iterator.hasNext()){
            iterator.next();
        }
    }
}
