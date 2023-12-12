package cn.meng;

import org.junit.Test;

import java.nio.charset.StandardCharsets;

public class AppTest {
    @Test
    public void test(){
        byte[] a = "d".getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    @Test
    
    public void test2(){
        String a = "";
        int aa = 2;
        for (int i = 0; i < 19; i++) {
            System.out.println(i & 3);
        }
    }
}
