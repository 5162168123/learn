package com.bjpowernod.java.threadsafe2;

import java.text.ParseException;


public class Test {
    public static void main(String[] args) throws ParseException {
        Account account = new Account("act001",10000);
        Thread t1 = new Thread(new AccountThread(account));
        Thread t2 = new Thread(new AccountThread(account));
        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        t2.start();

    }
}
