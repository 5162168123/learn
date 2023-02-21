package cn.meng;

import java.util.concurrent.atomic.AtomicLong;
import java.lang.String;

public class AtomicLongTest {

    private static AtomicLong atomicLong ;
    private volatile static AtomicLongTest instance;

    private AtomicLongTest(){
        this.atomicLong = new AtomicLong(0);
    }

    public static  AtomicLongTest getInstance(){
        if(instance == null){
            synchronized (AtomicLongTest.class){
                if(instance == null){
                    instance = new AtomicLongTest();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        AtomicLongTest instance = AtomicLongTest.getInstance();
        String a = "aa";
        System.out.println(a.getClass());
        String aClass = a.getClass().toString();
        System.out.println(aClass);
    }

}
