package cn.meng;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;


public class LogTest {
    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        while (true){
            logger.info("time"+System.currentTimeMillis()+"name"+Thread.currentThread().getName()+""+ Arrays.toString(Thread.currentThread().getStackTrace()));
        }
    }

}
