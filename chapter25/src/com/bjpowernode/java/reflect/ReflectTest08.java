package com.bjpowernode.java.reflect;

import com.bjpowernode.java.service.UserService;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectTest08 {
    public static void main(String[] args) throws Exception{
        Class className = Class.forName("com.bjpowernode.java.service.UserService");
        Method[] methods = className.getDeclaredMethods();
        for(Method method : methods){
            System.out.println(Modifier.toString(method.getModifiers()));
            System.out.println(method.getReturnType());
            System.out.println(method.getName());
            Class[] parameterTypes = method.getParameterTypes();
            for(Class parameter:parameterTypes){
                System.out.println(parameter.getSimpleName());
            }
        }
    }
}
