package cn.meng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.locks.LockSupport;

public class InterruptTest {
    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date parse = sf1.parse("2022-12-29 23:04:00");
        LockSupport.parkUntil(parse.getTime());
        System.out.println(1);


    }
}
