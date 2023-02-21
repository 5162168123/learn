package cn.meng;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestNanos {
    public static void main(String[] args) {
        final ReentrantLock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                lock.lock();
                Thread.sleep(6000);
                System.out.println("Thread 1 handle the lock");
                condition.signal();
//                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }).start();
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("Thread 2 handle the lock" );
                long l = 5000l;
                long l1 = TimeUnit.MILLISECONDS.toNanos(l);
                long l2 = condition.awaitNanos(l1);
                System.out.println(l2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }).start();
    }
}
