package cn.meng;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;


public class LogTest {
    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws  Exception{
        byte[] bytes = new byte[5*1024*1024];
        while(true){
            logger.info(new String(bytes));
        }


    }

}
