package cn.meng;

import java.util.Vector;

public class VectorDemo {
    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        for (int i = 0; i < 30; i++) {
            vector.add(i);
            System.out.println("vector's size is " + vector.size()+" capacity is :" + vector.capacity());
        }
    }
}
