package com.bjpowernode.java.reflect;

import java.lang.reflect.Constructor;

public class ReflectTest12 {
    public static void main(String[] args) throws  Exception{
        Class vipClass = Class.forName("com.bjpowernode.java.bean.Vip");
        Constructor constructor = vipClass.getConstructor();
        Object obj = constructor.newInstance();
        System.out.println(obj);
        Constructor constructor1 = vipClass.getConstructor(int.class,String.class);
        Object obj1 = constructor1.newInstance(11,"2222");
        System.out.println(obj1);
    }
}
