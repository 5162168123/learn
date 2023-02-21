package cn.meng;

import java.util.concurrent.locks.LockSupport;

public class ParkDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("挂起之前的操作");
            LockSupport.park();
            System.out.println("挂起之后的操作");
        });

        thread.start();
        new Thread(()->{
            try{
                for (int i = 0; i < 100; i++) {
                    Thread.sleep(1000);
                    System.out.println("等待"+(i+1)+"秒");

                }
            }catch (Exception e){
                e.printStackTrace();
            }

            LockSupport.unpark(thread);
        }).start();
    }
}
