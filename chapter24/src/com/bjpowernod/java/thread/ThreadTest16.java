package com.bjpowernod.java.thread;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ThreadTest16 {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        Thread t1 = new Thread(new Provider(list),"provider");
        Thread t2 = new Thread(new Consumer(list),"consumer");

        t1.start();
        t2.start();
    }
}

class Provider implements Runnable{

    public Provider(ArrayList list) {
        this.list = list;
    }

    private ArrayList list;
    @Override
    public void run() {
        while (true){

            synchronized (list){
//                System.out.println(Thread.currentThread().getName()+"=="+System.currentTimeMillis());
                if(list.size() == 0){
                    Object obj = new Object();
                    list.add(obj);
                    System.out.println("生产生生产 ====》 " + obj);
                    list.notifyAll();
                }
                try {
//                    System.out.println("provider wait");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}

class Consumer implements Runnable{

    public Consumer(ArrayList list) {
        this.list = list;
    }

    private ArrayList list;
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (list){
//                System.out.println(Thread.currentThread().getName()+"=="+System.currentTimeMillis());
                if (list.size() > 0) {
                    Object obj = list.remove(0);
                    System.out.println("消费者消费 ====》 " + obj);
                    list.notify();
                }
                try {
//                    System.out.println("consumer wait");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
