package com.bjpowernode.java.annotation7;

import java.lang.reflect.Field;

public class Test {
    public static void main(String[] args) throws Exception{
        Class className = Class.forName("com.bjpowernode.java.annotation7.MyAnnotationTest01");
        Field[] declaredFields = className.getDeclaredFields();

        boolean isOK = false;
        for(Field f : declaredFields){
            if(f.getName().equals("id") && f.getType().getSimpleName().equals("int")){

                isOK = true;
                break;
            }

        }
        if (!isOK) {
            throw new HasNotPropertyException("没有ID属性");
        }
    }
}
