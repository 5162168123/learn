package cn.meng.buffer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

public class FileChannelDemo2 {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(10);
        try(
                FileOutputStream out = new FileOutputStream("D:\\idea-project\\temp\\234.txt");
                FileChannel fileChannel = out.getChannel()
        ) {
            for (int i = 0; i < 10; i++) {
                new Thread( () ->{
                    ByteBuffer buffer = ByteBuffer.wrap("hello world\r".getBytes(StandardCharsets.UTF_8));
                    try {
                        fileChannel.write(buffer);
                        latch.countDown();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
