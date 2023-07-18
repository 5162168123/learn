package cn.meng.jdk.demo.function;

import java.util.function.Consumer;

public class ConsumerTest {
    public static void main(String[] args) {
        printNum("aaa", System.out::println);
        Consumer<String> c1 = System.out::println;
        Consumer<String> c2 = (t) -> {
            System.out.println(t.length());
        };
        c1.andThen(c2).accept("123");

    }

    public static void printNum(String str,Consumer<String> consumer){
        consumer.accept(str);
    }
}
