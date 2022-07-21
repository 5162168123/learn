package com.bjpowernod.java.exam3;

public class Exam01 {
    public static void main(String[] args) throws InterruptedException {
        MyClass mc = new MyClass();
        MyClass mc2 = new MyClass();
        Thread t1 = new MyThread(mc);
        Thread t2 = new MyThread(mc2);
        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        Thread.sleep(1000);
        t2.start();
    }

}

class MyThread extends Thread{
    MyClass mc;

    public MyThread(MyClass mc) {
        this.mc = mc;
    }

    @Override
    public void run() {
        if(Thread.currentThread().getName() == "t1"){
            try {
                mc.doSome();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(Thread.currentThread().getName() == "t2"){
            mc.doOther();
        }
    }
}

class MyClass{
    public  void doSome() throws InterruptedException {
        System.out.println("doSome begin");
        Thread.sleep(5000);
        System.out.println("doSome end");
    }

    public  void doOther(){
        System.out.println("doOther begin");
        System.out.println("doOther over");
    }
}