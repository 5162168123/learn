package cn.meng;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class IOWaitTest {
    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            int finalI = i;
            byte[] bytes = new byte[1024*1024];
            new Thread(() -> {
                try(
                        OutputStream out = new FileOutputStream(new File(finalI +"123.txt"))
                ){
                    while (true){
                        out.write(bytes);
                        out.flush();
                    }
                }catch (Exception e){

                }
            }).start();
        }


    }
}
