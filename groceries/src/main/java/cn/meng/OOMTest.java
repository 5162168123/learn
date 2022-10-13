package cn.meng;

import java.util.LinkedList;

public class OOMTest {
    static LinkedList<byte[]> list = new LinkedList<>();

    public static void main(String[] args) {

        for(;;){
            list.add(new byte[1024]);
        }
    }
}
