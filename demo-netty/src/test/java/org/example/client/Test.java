package org.example.client;

import io.netty.util.internal.SystemPropertyUtil;

import java.util.Properties;

public class Test {
    @org.junit.Test
    public void test1(){
        String s = SystemPropertyUtil.get("sun.desktop");
        Properties properties = System.getProperties();
        for (Object o : properties.keySet()) {
            System.out.println("property " + o + " is :"+properties.getProperty((String)o));

        }

        System.out.println(s);
        int ii = Runtime.getRuntime().availableProcessors();
        System.out.println(ii);
        System.out.println(getClass());
    }
}
