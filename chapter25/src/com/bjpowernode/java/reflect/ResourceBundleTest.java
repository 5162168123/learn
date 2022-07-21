package com.bjpowernode.java.reflect;

import jdk.management.resource.ResourceContext;

import java.util.ResourceBundle;

/**
 * 文件扩展名必须properties
 * 必须在类路径下
 */
public class ResourceBundleTest {
    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("classinfo2");
        String className = bundle.getString("className");
        System.out.println(className);
    }
}
