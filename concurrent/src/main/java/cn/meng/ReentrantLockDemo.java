package cn.meng;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantLockDemo {
    private static volatile int i = 0;
    private static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(ReentrantLockDemo::add).start();
        new Thread(ReentrantLockDemo::add).start();


    }

    static void add(){

        for (int j = 0; j < 10000; j++) {
            reentrantLock.lock();
            System.out.println(++i);
            reentrantLock.unlock();
        }
    }
}
