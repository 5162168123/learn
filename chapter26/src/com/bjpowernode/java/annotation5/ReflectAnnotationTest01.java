package com.bjpowernode.java.annotation5;

import java.lang.annotation.Annotation;

public class ReflectAnnotationTest01 {
    public static void main(String[] args) throws Exception{
        Class className = Class.forName("com.bjpowernode.java.annotation5.MyAnnotationTest");

        boolean annotationPresent = className.isAnnotationPresent(MyAnnotation.class);

        if(annotationPresent){
            MyAnnotation annotation = (MyAnnotation)className.getAnnotation(MyAnnotation.class);
            System.out.println(annotation.value());
        }
    }
}
