package com.bjpowernode.java.reflect;

import com.bjpowernode.java.bean.Student;

import java.lang.reflect.Field;

public class ReflectTest07 {
    public static void main(String[] args) throws Exception{
        Student student = new Student();
        student.no = 1111;
        System.out.println(student.no);

        Class className = Class.forName("com.bjpowernode.java.bean.Student");
        Object object = className.newInstance();
        Field no = className.getDeclaredField("no");
        no.set(object,222222);
        System.out.println(no.get(object));


        Field name = className.getDeclaredField("name");
        name.setAccessible(true);
        name.set(object,"jackson");

        System.out.println(name.get(object));
    }
}
