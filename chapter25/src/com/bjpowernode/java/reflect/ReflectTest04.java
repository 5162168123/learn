package com.bjpowernode.java.reflect;

import com.bjpowernode.java.bean.User;
import jdk.internal.util.xml.impl.Input;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReflectTest04 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
       /* String path = Thread.currentThread().getContextClassLoader().getResource("classinfo2.properties").getPath();
        FileReader fileReader = new FileReader(path);*/

        InputStream fileReader = Thread.currentThread().getContextClassLoader().getResourceAsStream("classinfo2.properties");
        Properties pro = new Properties();
        pro.load(fileReader);
        fileReader.close();
        String className = pro.getProperty("className");
        Class c = Class.forName(className);
        User u = (User)c.newInstance();
        System.out.println(u);
        Object obj = c.newInstance();
        System.out.println(obj);
    }
}
