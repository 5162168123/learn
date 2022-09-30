package cn.meng.buffer;

import javax.accessibility.AccessibleAction;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class FileChannelDemi {
    public static void main(String[] args) throws FileNotFoundException {
        try(
                FileOutputStream out = new FileOutputStream("D:\\idea-project\\temp\\123.txt");
                FileChannel fileChannel = out.getChannel()
                ) {
            ByteBuffer buffer = ByteBuffer.wrap("hello world".getBytes(StandardCharsets.UTF_8));
            fileChannel.write(buffer);
            System.out.println("position now is " + buffer.position());
            System.out.println("limit now is " + buffer.limit());

            //rewind position to 0
            buffer.rewind();
            //末尾追加，顺序写
            fileChannel.write(buffer);

            //在hello world 中间插入某个信息，随机写，在文件随机位置写入数据
            buffer.rewind();
            //fileChannel也有position的概念
            //channel的position也代表了文件的位置
            //默认在channel文件末尾追加
            fileChannel.write(buffer,6);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
