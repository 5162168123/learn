package com.bjpowernod.java.thread;

public class ThreadTest10 {
    public static void main(String[] args) throws InterruptedException {
        MyRunnable4 m = new MyRunnable4();
        new Thread(m).start();
        Thread.sleep(2000);
        m.run = false;
    }

}
class MyRunnable4 implements Runnable{

    boolean run = true;
    @Override
    public void run() {
            for (int i = 0; i < 10; i++) {
                if(run){

                System.out.println(Thread.currentThread().getName() + " ==> " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                    return;
                }

        }
    }
}
