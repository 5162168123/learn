package com.bjpowernode.java.reflect;

public class ReflectTest02 {
    public static void main(String[] args) {
        try {
            Class c = Class.forName("com.bjpowernode.java.bean.User");
            Object obj = c.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
