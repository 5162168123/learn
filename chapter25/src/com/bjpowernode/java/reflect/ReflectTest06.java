package com.bjpowernode.java.reflect;


import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReflectTest06 {
    public static void main(String[] args) throws Exception {
        StringBuilder s = new StringBuilder();
//        Class classname = Class.forName("com.bjpowernode.java.bean.Student");
        Class classname = Class.forName("java.lang.Integer");
        Field[] fields = classname.getDeclaredFields();
        s.append(Modifier.toString(classname.getModifiers()) + " class " + classname.getSimpleName() + "{\n");

        for(Field f : fields){
            s.append("\t" + Modifier.toString(f.getModifiers()) + " "+ f.getType().getSimpleName() + " " + f.getName() + ";\n");
        }
        System.out.println(s);

    }


}
