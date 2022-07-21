package com.bjpowernode.java.reflect;

public class AboutPath {
    public static void main(String[] args) {
        String path = Thread.currentThread().getContextClassLoader().getResource("classinfo2.properties").getPath();
        System.out.println(path);
        String path2 = Thread.currentThread().getContextClassLoader().getResource("com/bjpowernode/java/bean").getPath();
        System.out.println(path2);
    }
}
