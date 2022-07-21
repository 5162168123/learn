package com.bjpowernod.java.thread;

public class ThreadTest05 {
    public static void main(String[] args) {
        MyThread2 t = new MyThread2();
        //t.setName("ttttt");
        String tName = t.getName();
        System.out.println(tName);
        t.start();
    }
}

class MyThread2 extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(System.currentTimeMillis());
            //System.out.println("分支线程 ==> " + i);

        }
    }
}
