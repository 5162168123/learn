package cn.meng;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        App app = new App();
        new Thread(app::test);
    }
    public void test(){
        System.out.println(123);
    }
    public void test1(){
        Map<String,Object> map = new HashMap<>();
        map.put("1",null);
    }
}
