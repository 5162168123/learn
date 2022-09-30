package cn.meng.buffer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class FileLockDemo {
    public static void main(String[] args) {
        try(
                RandomAccessFile out = new RandomAccessFile("D:\\idea-project\\temp\\123.txt","rw");
                FileChannel fileChannel = out.getChannel()
                ){
            //true共享锁，false 独占锁，加了独占锁其他不能再加独占锁以及写入
            //共享锁独占锁之间互斥
            //加了共享锁之后还可以加共享锁
            //加共享锁不能写数据
            //加了独占锁不能加共享锁
            fileChannel.lock(0,Integer.MAX_VALUE,false);
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
            int read = fileChannel.read(buffer);
            System.out.println(read);
            System.out.println("枷锁陈工");
//            fileChannel.write(ByteBuffer.wrap("abc".getBytes(StandardCharsets.UTF_8)));
            System.out.println("写入成功");
            Thread.sleep(60*60*1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
