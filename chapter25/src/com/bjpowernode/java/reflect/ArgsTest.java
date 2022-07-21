package com.bjpowernode.java.reflect;

public class ArgsTest {
    public static void main(String[] args) {
        m();
        m(10);
        m(10,20);
    }
    public static void m(int ...args){
        System.out.println("m执行了。。。");
    }
}
