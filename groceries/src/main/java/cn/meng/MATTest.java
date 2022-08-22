package cn.meng;

import java.util.ArrayList;
import java.util.LinkedList;

public class MATTest {
    public static void main(String[] args) throws Exception {
        for (int j = 0;j<10;j++) {
            new Thread(()->{
                LinkedList<test> a = new LinkedList<test>();
                while (true) {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    a.add(new test());
                }
            }).start();
        }
    }
    static class test{
        String a ="mengxianyu";
    }
}
