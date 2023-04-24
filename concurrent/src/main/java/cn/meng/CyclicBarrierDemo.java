package cn.meng;

import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("合并工作万恒");
            }
        });

        new Thread( () ->{
            System.out.println("线程1工作完事儿");
            try {
                cyclicBarrier.await();
//                Thread.sleep(1000);
                System.out.println(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread( () ->{
            System.out.println("线程2工作完事儿");
            try {
                cyclicBarrier.await();
//                Thread.sleep(1000);
                System.out.println(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread( () ->{
            System.out.println("线程3工作完事儿");
            try {
                cyclicBarrier.await();
//                Thread.sleep(1000);
                System.out.println(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
