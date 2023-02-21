package cn.meng;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println("线程1等待1秒");
                    Thread.sleep(1000);
                    System.out.println("线程1开始countDown");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println("线程2等待1秒");
                    Thread.sleep(1000);
                    System.out.println("线程2开始countDown");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        System.out.println("main线程等待");
        latch.await();
        System.out.println("完事儿了");
    }
}
