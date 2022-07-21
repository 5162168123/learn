package cn.meng.jdk.demo.collection.homework;

import java.util.HashMap;

import static java.util.Objects.hash;

public class HashMapDemo {
    public static void main(String[] args) {
        HashMap<String,String> map  = new HashMap<String, String>();
        map.put("1","张三");
        map.put("2","lisi");
        map.put("3","ssss");
        map.forEach((k,v) -> System.out.println(k));
        System.out.println(hash(1));
        Integer i = 1;
        System.out.println(i.hashCode());

    }
}
