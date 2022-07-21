package com.bjpowernode.java.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public class ReflectTest09 {
    public static void main(String[] args) throws  Exception{
        Class className = Class.forName("com.bjpowernode.java.service.UserService");
        StringBuilder s = new StringBuilder();
        s.append(Modifier.toString(className.getModifiers()) + " class " + className.getSimpleName() + " { \n");
        Method[] methods = className.getDeclaredMethods();
        for(Method method : methods){
            s.append("\t" + Modifier.toString(method.getModifiers()) + " " + method.getReturnType() + " " +
                    method.getName() + "(");
            Parameter[] parameters = method.getParameters();
            for(Parameter parameter :parameters){
                s.append(parameter.getType().getSimpleName() + " " + parameter.getName());

                if (parameter != parameters[parameters.length-1]){
                    s.append(",");
                }
            }
            s.append(") {\n");


        }
        System.out.println(s);

    }
}
