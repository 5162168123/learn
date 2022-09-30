package cn.meng.buffer;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileLockDemo2 {
    public static void main(String[] args) {
        try(
                RandomAccessFile out = new RandomAccessFile("D:\\idea-project\\temp\\123.txt","rw");
                FileChannel fileChannel = out.getChannel()
                ){
            //true共享锁，false 独占锁，加了独占锁其他不能再加独占锁以及写入
            //共享锁独占锁之间互斥
            //加了共享锁之后还可以加共享锁
            //加共享锁不能写数据
            System.out.println("get set");
            fileChannel.lock(0,Integer.MAX_VALUE,true);
            System.out.println("枷锁陈工");
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
            System.out.println("get start reading");
            int read = fileChannel.read(buffer);
            System.out.println(read);

//            fileChannel.write(ByteBuffer.wrap("abc".getBytes(StandardCharsets.UTF_8)));
            System.out.println("写入成功");
            Thread.sleep(60*60*1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
