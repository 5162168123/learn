package cn.meng;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Hello world!
 *
 */
public class App 
{
    static volatile AtomicInteger a = new AtomicInteger(0);
    public static void main( String[] args ) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        System.out.println(simpleDateFormat.format(new Date(System.currentTimeMillis())).toString());
    }
    public void test(){
        System.out.println(123);
    }
    public void test1(){
        Map<String,Object> map = new HashMap<>();
        map.put("1",null);
    }
}
