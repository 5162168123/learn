package cn.meng;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {
    public static void main(String[] args) {
        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();
        map.put("k1","v1");
    }
}
