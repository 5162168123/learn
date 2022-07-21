package com.bjpowernod.java.thread;

public class ThreadTest08 {
    public static void main(String[] args) throws InterruptedException {
        MyThread3 thread3 = new MyThread3();
        thread3.start();
        Thread.sleep(1000);
        thread3.interrupt();

    }
}

class MyThread3 extends Thread{
    @Override
    public void run() {
        System.out.println(this.getName() + "==> start" );
        try {
            Thread.sleep(1000*60*60*24);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getName() + "==> end");
    }
}

class MyRunnable2 implements Runnable{

    @Override
    public void run() {
        System.out.println( "==> start" );
        try {
            Thread.sleep(1000*60*60*24);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("==> end");
    }
}
