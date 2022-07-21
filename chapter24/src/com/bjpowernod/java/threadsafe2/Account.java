package com.bjpowernod.java.threadsafe2;

public class Account {
    private String accountNo;
    private double balance;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Account() {
    }

    public Account(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public void withdraw(double money) throws InterruptedException {

        synchronized (accountNo){
            double before = this.balance;
            double after = before - money;
            Thread.sleep(1000);
            this.setBalance(after);
        }

    }
}
