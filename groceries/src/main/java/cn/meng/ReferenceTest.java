package cn.meng;

import java.util.HashMap;
import java.util.Map;

public class ReferenceTest {

    public static void main(String[] args) {
        Map<String,String> a = new HashMap<>();
        a.put("1","1");
        a.put("2","2");
        combine(a);
        System.out.println(a);

    }
    /**
     * sdfsdf
     */
    static void combine(Map<String,String> a ){

        a = new HashMap<>();
        a.put("a","b");
    }
}
