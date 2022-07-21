package com.bjpowernode.java.reflect;

import com.bjpowernode.java.service.UserService;

import java.lang.reflect.Method;
import java.util.Timer;

public class ReflectTest10 {
    public static void main(String[] args) throws Exception {
        UserService userService = new UserService();
        boolean login = userService.login("admin", "12322");
        System.out.println(login ? "登录成功" : "登录失败");


        Class className = Class.forName("com.bjpowernode.java.service.UserService");
        Object obj = className.newInstance();
        Method loginMethod = className.getMethod("login", String.class, String.class);

        Object obReturn = loginMethod.invoke(obj, "admin", "123");
        System.out.println(obReturn);
    }
}
