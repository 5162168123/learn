package cn.meng.jdk.demo.function;

import java.util.Random;
import java.util.function.Supplier;

public class SupplierTest {
    public static void main(String[] args) {
        Supplier<Integer> s1 = ()-> new Random().nextInt(20);
        Supplier<Integer> s2 = ()-> {
            int i = new Random().nextInt();
            if(i < 100){
                return 10;
            }else{
                return 20;
            }
        };


        Integer integer = s1.get();

    }

}
