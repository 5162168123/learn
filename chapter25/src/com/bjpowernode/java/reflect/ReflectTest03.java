package com.bjpowernode.java.reflect;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReflectTest03 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        FileReader fileReader = new FileReader("chapter25/classinfo.properties");
        Properties pro = new Properties();
        pro.load(fileReader);
        fileReader.close();
        String className = pro.getProperty("className");
        String className1 = pro.getProperty("className1");
        Class c = Class.forName(className);
        Class c1 = Class.forName(className1);
        Object obj = c.newInstance();
        Object obj1 = c.newInstance();

        System.out.println(obj);
        System.out.println(obj1);
    }
}
