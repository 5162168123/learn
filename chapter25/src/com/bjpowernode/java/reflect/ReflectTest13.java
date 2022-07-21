package com.bjpowernode.java.reflect;

public class ReflectTest13 {
    public static void main(String[] args) throws Exception{
        Class className = Class.forName("java.lang.String");
        Object superObj = className.getSuperclass();
        System.out.println(superObj);

        Class[] interfaces = className.getInterfaces();
        for(Class in : interfaces){
            System.out.println(in.getName());
        }
    }
}
