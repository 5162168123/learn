package cn.meng;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.LockSupport;

public class SemaphoreDemo {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(10);
        Thread thread = new Thread(() -> {
            while (!Thread.interrupted()) {
                System.out.println(Thread.activeCount());
                LockSupport.parkUntil(System.currentTimeMillis() + 100);
            }
        });
        thread.setDaemon(true);
        thread.start();
        for (int i = 0; i < 100; i++) {
            semaphore.acquire();
            new Thread(() -> {
                LockSupport.parkUntil(System.currentTimeMillis() + 400);
                semaphore.release();
            }).start();


        }

    }
}
