package cn.meng;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantLockDemo {
    private static volatile int i = 0;
    private static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
//        new Thread(ReentrantLockDemo::add).start();
//        new Thread(ReentrantLockDemo::add).start();
        new Thread(() -> {
            reentrantLock.lock();
            System.out.println(Thread.currentThread().getName()+" hold the lock");
            try {
                Thread.sleep(10*1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                reentrantLock.unlock();
                System.out.println(Thread.currentThread().getName()+" release the lock");
            }
        }).start();
        new Thread(() -> {
            reentrantLock.lock();
            System.out.println("Thread-2 hold the lock");
            try {
                Thread.sleep(1*1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                reentrantLock.unlock();
                System.out.println("Thread-2 release the lock");
            }
        }).start();
    }

    static void add(){
        for (int j = 0; j < 10000; j++) {
            reentrantLock.lock();
            System.out.println(++i);
            reentrantLock.unlock();
        }
    }
}
