package cn.meng.buffer;

import java.nio.ByteBuffer;

public class BufferDemo {
    public static void main(String[] args) {
        byte[] data = new byte[]{1,2,3};

        ByteBuffer buffer = ByteBuffer.wrap(data);
        //buffer 的容量
        System.out.println(buffer.capacity());
        //buffer的当前指针位置
        System.out.println(buffer.position());
        //buffer 最大读取限度
        System.out.println(buffer.limit());


        System.out.println(buffer.get());
        System.out.println("position now is " + buffer.position());
    }
}
