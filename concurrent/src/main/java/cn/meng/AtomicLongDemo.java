package cn.meng;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongDemo {
    public static void main(String[] args) {
        AtomicLong atomicLong = new AtomicLong(1);
        long andAdd = atomicLong.getAndAdd(1l);
        System.out.println(andAdd);
    }
}
