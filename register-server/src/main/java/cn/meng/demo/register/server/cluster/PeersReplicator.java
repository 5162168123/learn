package cn.meng.demo.register.server.cluster;

import cn.meng.demo.register.server.web.AbstractRequest;
import cn.meng.demo.register.server.web.CancelRequest;
import cn.meng.demo.register.server.web.HeartbeatRequest;
import cn.meng.demo.register.server.web.RegisterRequest;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 集群同步组件
 */
public class PeersReplicator {

    //集群同步batch包生成时间间隔
    private static final long PEERS_REPLICATE_BATCH_INTERVAL = 500L;
    private static final PeersReplicator instance = new PeersReplicator();
    /**
     * 第一层队列，接收高并发写入
     */
    private ConcurrentLinkedDeque<AbstractRequest> acceptorQueue = new ConcurrentLinkedDeque<>();
    /**
     * 第二梯队
     */
    private LinkedBlockingDeque<AbstractRequest> batchQueue = new LinkedBlockingDeque<>();

    /**
     * 第三层，存放打包好的信息，等待发送同步
     *
     */
    private LinkedBlockingDeque<PeersReplicatorBatch> sendQueue = new LinkedBlockingDeque<>();

    private PeersReplicator(){
        //开启接收请求以及打包 的线程
        AcceptorBatchThread thread= new AcceptorBatchThread();
        thread.setDaemon(true);
        thread.start();
        PeersReplicateThread batchThread = new PeersReplicateThread();
        batchThread.setDaemon(true);
        batchThread.start();
    }
    public static PeersReplicator getInstance(){
        return instance;
    }

    /**
     * 同步服务注册信息
     */
    public void replicateRegister(RegisterRequest request){
        request.setType(AbstractRequest.REGISTER_REQUEST);
        acceptorQueue.offer(request);
    }

    /***
     * 同步服务下线信息
     */
    public void replicateCancel(CancelRequest request){
        request.setType(AbstractRequest.CANCEL_REQUEST);

        acceptorQueue.offer(request);

    }

    /**
     * 同步心跳
     */
    public void replicateHeartBeat(HeartbeatRequest request){
        request.setType(AbstractRequest.HEARTBEAT_REQUEST);

        acceptorQueue.offer(request);
    }

    class AcceptorBatchThread extends Thread{

        @Override
        public void run() {
            long batchGeneration = System.currentTimeMillis();
            while(true){
                //从第一层列队中获取请求，并放入第二列队
                try {
                AbstractRequest poll;
                if((poll = acceptorQueue.poll()) != null){
                        batchQueue.put(poll);
                    }

                //设定打包策略
                long now = System.currentTimeMillis();
                if(now-batchGeneration >= PEERS_REPLICATE_BATCH_INTERVAL){
                    //如果第二梯队长度大于0.生成一个batch
                    if(batchQueue.size() >0){
                        PeersReplicatorBatch batch = createBatch();
                        batchQueue.clear();
                        sendQueue.offer(batch);
                    }
                    batchGeneration = System.currentTimeMillis();
                }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private PeersReplicatorBatch createBatch() {
            Iterator<AbstractRequest> iterator = batchQueue.iterator();
            PeersReplicatorBatch batch = new PeersReplicatorBatch();

            while (iterator.hasNext()){
                batch.add(iterator.next());
            }
            return batch;
        }
    }


    /**
     * 进行集群同步的线程
     */
    class PeersReplicateThread extends  Thread{
        @Override
        public void run() {

            while(true){
                try {
                    //阻塞式获取第一个元素
                    PeersReplicatorBatch batch = sendQueue.take();
                    //发送http请求，给每个registerServer发送同步请求
                    System.out.println("给每个registerServer发送同步请求");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        LinkedBlockingQueue<String> a = new LinkedBlockingQueue<>();
        System.out.println(a.size());
        new Thread(()->{
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(1000);
                    System.out.println("等待："+ (i+1) + "秒");
                }

                a.offer("123");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                System.out.println("线程2开始take");
                String b = a.take();
                System.out.println(b);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
