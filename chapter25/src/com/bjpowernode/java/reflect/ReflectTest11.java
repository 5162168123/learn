package com.bjpowernode.java.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public class ReflectTest11 {
    public static void main(String[] args) throws Exception{
        Class vipClass = String.class;
        Constructor[] constructors = vipClass.getConstructors();
        StringBuilder s = new StringBuilder();
        s.append(Modifier.toString(vipClass.getModifiers()) + " class " + vipClass.getSimpleName() + " {\n" );
        for(Constructor constructor : constructors){
            s.append(Modifier.toString(constructor.getModifiers()) + " " + vipClass.getSimpleName() + "(");
            Class[] parameterTypes = constructor.getParameterTypes();
            for(Class parameter:parameterTypes){
                s.append(parameter.getSimpleName());
                if(parameter != parameterTypes[parameterTypes.length-1]){
                    s.append(",");
                }
            }
            s.append("){}\n");
        }
        s.append("}");
        System.out.println(s);
    }
}
