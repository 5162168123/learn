package cn.meng.buffer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class CopyFile {
    public static void main(String[] args) {
        try(
                FileChannel fci = new FileInputStream("D:\\idea-project\\temp\\123.txt").getChannel();
                FileChannel fcw = new FileOutputStream("D:\\idea-project\\temp\\234.txt").getChannel()
        ){
            ByteBuffer allocate = ByteBuffer.allocate(1024);
            int count = 0;
            while ((count = fci.read(allocate)) != -1){
                allocate.flip();
                fcw.write(allocate);
                allocate.clear();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
