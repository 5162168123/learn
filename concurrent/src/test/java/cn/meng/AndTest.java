package cn.meng;

import org.junit.Test;

public class AndTest {
    @Test
    public void test(){
        int capacity   = (1 << 29) - 1;
        System.out.println(capacity);
        System.out.println(~capacity);
    }
}
