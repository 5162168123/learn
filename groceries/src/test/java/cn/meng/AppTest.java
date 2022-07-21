package cn.meng;

import static org.junit.Assert.assertTrue;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import sun.awt.image.ImageWatched;
import sun.rmi.runtime.Log;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void test1() throws UnsupportedEncodingException {
        String a = "select%20sum(%22sum%22)%2Csum(%22TPS%22)%2Csum(%22avg%22)%2F1000%2Csum(%2290%22)%2F1000%2Csum(%2295%22)%2F1000%2Csum(%2299%22)%2F1000%2Csum(%22error%22)%2Csum(%22error%22)%2Fsum(%22sum%22)%20from%20(SELECT%20sum(%22count%22)%20as%20%22sum%22%20%2Cmean(%22count%22)%20%2F%205%20as%20%22TPS%22%2Cmean(%22avg%22)%20as%20%22avg%22%20%2Cmean(%22pct90.0%22)%20as%20%2290%22%2Cmean(%22pct95.0%22)%20as%20%2295%22%2Cmean(%22pct99.0%22)%20as%20%2299%22%20FROM%20%22jmeter%22%20WHERE%20(%22application%22%20%3D~%20%2F%5E%E7%A7%81%E4%BA%AB%E5%95%86%E5%9F%8E%E8%A7%A3%E9%94%81%E7%94%9F%E5%91%BD%E5%AF%86%E7%A0%81%E6%B4%BB%E5%8A%A8%E7%AC%AC%E4%BA%8C%E5%AD%A3%24%2F%20AND%20%22statut%22%20%3D%20%27ok%27)%20AND%20time%20%3E%3D%201656708463272ms%20and%20time%20%3C%3D%201657314181800ms%20%20fill(null))%20%2C%20(select%20sum(%22count%22)%20as%20%22sum%22%2Csum(%22count%22)%20as%20%22error%22%20FROM%20%22jmeter%22%20WHERE%20(%22application%22%20%3D~%20%2F%5E%E7%A7%81%E4%BA%AB%E5%95%86%E5%9F%8E%E8%A7%A3%E9%94%81%E7%94%9F%E5%91%BD%E5%AF%86%E7%A0%81%E6%B4%BB%E5%8A%A8%E7%AC%AC%E4%BA%8C%E5%AD%A3%24%2F%20AND%20%22statut%22%20%3D%20%27ko%27)%20AND%20time%20%3E%3D%201656708463272ms%20and%20time%20%3C%3D%201657314181800ms%20)group%20by%20%22transaction%22";
//        String encode = URLEncoder.encode(a, "UTF-8");
        String decode = URLDecoder.decode(a,"UTF-8");
        System.out.println(decode);
    }
    @Test
    public void test2() throws ParseException {
        Integer a = 10;
        int b = 0;
        if(a == null || a ==0){

        }





    }
}
