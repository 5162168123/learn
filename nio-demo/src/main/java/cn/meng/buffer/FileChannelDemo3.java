package cn.meng.buffer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class FileChannelDemo3 {
    public static void main(String[] args) {
        try(
                FileInputStream out = new FileInputStream("D:\\idea-project\\temp\\123.txt");
                FileChannel fileChannel = out.getChannel()
        ){
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
            fileChannel.read(buffer);
            System.out.println("position now " + buffer.position());
            System.out.println("limit now " + buffer.limit());
            buffer.flip();
            byte[] bytes = new byte[buffer.limit()];
            buffer.get(bytes);
            System.out.println(new String(bytes, StandardCharsets.UTF_8));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
