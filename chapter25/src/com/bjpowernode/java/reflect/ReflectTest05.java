package com.bjpowernode.java.reflect;

import sun.reflect.annotation.ExceptionProxy;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReflectTest05 {
    public static void main(String[] args) throws Exception {
        Class c = Class.forName("com.bjpowernode.java.bean.Student");
        Field[] fields = c.getFields();
        System.out.println(fields.length);
        Field f = fields[0];
        System.out.println(f.getName());
        System.out.println("+++++++++++++++++++++++++++++");
        Field[] fields1 = c.getDeclaredFields();
        System.out.println(fields1.length);
        for(Field field : fields1){
            int i = field.getModifiers();
            System.out.println(Modifier.toString(i));
            Class type = field.getType();
            System.out.println(type.getSimpleName());
            System.out.println(field.getName());
        }
    }
}
