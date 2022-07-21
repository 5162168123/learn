package com.bjpowernode.java.annotation6;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class MyAnnotationTest01 {
    @MyAnnotation
    public void doSome(){

    }

    public static void main(String[] args) throws Exception{
        Class className = Class.forName("com.bjpowernode.java.annotation6.MyAnnotationTest01");

        Method doSome = className.getDeclaredMethod("doSome");
        MyAnnotation annotation = doSome.getAnnotation(MyAnnotation.class);
        System.out.println(annotation.name());
        System.out.println(annotation.password());
    }
}
