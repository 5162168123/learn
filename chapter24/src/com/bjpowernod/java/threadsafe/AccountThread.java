package com.bjpowernod.java.threadsafe;

public class AccountThread implements Runnable{
    Account account;

    public AccountThread(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        double money = 5000.00;
        try {
            account.withdraw(money);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" 取款成功，余额：" + account.getBalance());
    }
}
