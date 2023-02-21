package cn.meng.dfs.datanode.server;

import java.util.concurrent.CountDownLatch;

/**
 * 负责跟一组namenode中的某一个进行通信的线程组件
 *
 */
public class NameNodeServiceActor {
    public void register(CountDownLatch latch) {
        RegisterThread registerThread = new RegisterThread(latch);
        registerThread.start();
    }
    class RegisterThread extends Thread{
        CountDownLatch latch;
        public RegisterThread(CountDownLatch latch){
            this.latch = latch;
        }

        @Override
        public void run() {
            System.out.println("向自己负责的节点发送注册请求。。。。");
            latch.countDown();
        }
    }
}
