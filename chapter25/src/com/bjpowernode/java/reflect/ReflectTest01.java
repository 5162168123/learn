package com.bjpowernode.java.reflect;

public class ReflectTest01 {
    public static void main(String[] args) {
        Class c1 = null;
        try {
            c1 = Class.forName("java.lang.String");
            Class c2 = Class.forName("java.util.Date");
            Class c3 = Class.forName("java.lang.Integer");
            Class c4 = Class.forName("java.lang.System");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String s = "abc";
        Class x  = s.getClass();
        System.out.println(c1 == x);

        Class z = String.class;
        Class k = int.class;
        Class l = double.class;
    }
}
