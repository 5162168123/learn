package cn.meng.direct;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class DirectBufferTest {
    public static void main(String[] args) throws InterruptedException {
        List<ByteBuffer> buffers = new ArrayList<>();
        int count = 0;
        while (true){
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
            buffers.add(buffer);
            count += 1;
            Thread.sleep(100);
            System.out.println("current directBuffer is " + count +"KB");
        }
    }
}
