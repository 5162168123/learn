package cn.meng;

import java.io.File;
import java.io.FileReader;

public class ThrowTest {
    public void test1() throws RuntimeException{
        System.out.println(123);
    }

    public void test2(){
        test1();
        File a = new File("");
    }
}
