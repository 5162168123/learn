package com.bjpowernode.java.annotation2;

public @interface MyAnnotation {
    String name();
    String color();
    int age() default 25;
}
