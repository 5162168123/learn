package com.bjpowernod.java.thread;

import java.sql.SQLOutput;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadTest15 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask task = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                int i = 1;
                int ii = 2;
                System.out.println("call method begin");
                Thread.sleep(3000);
                System.out.println("call method end");
                return i+ii;
            }
        });


        Thread t = new Thread(task);
        t.start();
        System.out.println(task.get());
    }
}
