package cn.meng;

import static org.junit.Assert.assertTrue;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jdk.nashorn.internal.parser.JSONParser;
import org.junit.Test;
import sun.awt.image.ImageWatched;
import sun.rmi.runtime.Log;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
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

    @Test
    public void test3() throws UnsupportedEncodingException {
        String a = "(1%20-%20avg(rate(node_cpu_seconds_total%7Binstance%3D~%22192%5C%5C.168%5C%5C.124%5C%5C.12%3A9100%7C192%5C%5C.168%5C%5C.124%5C%5C.13%3A9100%22%2Cmode%3D%22idle%22%7D%5B2m%5D))%20by%20(instance))*100";
        String decode = URLDecoder.decode(a, "utf-8");
        System.out.println(decode);
        String code = "(1 - avg(rate(node_cpu_seconds_total{instance=~\"192.168.124.12:9100|192.168.124.13:9100\",mode=\"idle\"}[2m])) by (instance))*100";
//        String code = "(1 - avg(rate(node_cpu_seconds_total{instance=~\"192\\\\.168\\\\.124\\\\.12:9100|192\\\\.168\\\\.124\\\\.13:9100\",mode=\"idle\"}[2m])) by (instance))*100\n";
        String encode = URLEncoder.encode(code, "utf-8");
        System.out.println(encode);

    }

    @Test
    public void test4(){
        LinkedList<String> a = new LinkedList<>();
        a.add("a");
        a.add("b");
        a.forEach((x) ->{
            System.out.println(x);
        });
        String s = JSON.toJSONString(a);
        System.out.println(s);
        String b = "{\"aa\":[\"a\",\"b\"]}";
        LinkedList<String> linkedList = JSON.parseObject(b, LinkedList.class);
        System.out.println(linkedList);
    }

    @Test
    public void test5() throws ParseException {
        SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String a = "2022-08-26 16:34:45";
        String b = "2022-08-26 16:34:00";
        System.out.println(SIMPLE_DATE_FORMAT.parse(a).getTime());
        System.out.println(SIMPLE_DATE_FORMAT.parse(b).getTime());

        System.out.println(SIMPLE_DATE_FORMAT.format(new Date(1661518030000L)));
        long ab = 1661502842001L;
        System.out.println(ab/1000);
    }

    public void test6() throws ParseException {
        SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String a = "2022-08-26 16:34:45";
        Date parse = SIMPLE_DATE_FORMAT.parse(a);

    }

    @Test
    public void test7(){
        String a = "{\"status\":\"success\",\"data\":{\"resultType\":\"matrix\",\"result\":[{\"metric\":{\"instance\":\"192.168.124.12:9100\"},\"values\":[[1661502885,\"\"],[1661502900,\"1.9619047619025554\"],[1661502915,\"1.9333333333356961\"],[1661502930,\"1.9523809523802615\"],[1661502945,\"1.8380952380955162\"],[1661502960,\"1.7714285714256706\"],[1661502975,\"1.7333333333330647\"],[1661502990,\"1.7809523809514172\"],[1661503005,\"1.7714285714291345\"],[1661503020,\"1.7904761904736999\"],[1661503035,\"1.752380952381094\"],[1661503050,\"1.8666666666658505\"],[1661503065,\"1.9142857142876557\"],[1661503080,\"1.9428571428579788\"],[1661503095,\"1.876190476191597\"],[1661503110,\"1.9142857142876557\"],[1661503125,\"1.904761904761909\"],[1661503140,\"1.9428571428579788\"],[1661503155,\"1.9333333333322211\"],[1661503170,\"1.9428571428579788\"],[1661503185,\"1.9333333333322211\"],[1661503200,\"1.9619047619060082\"],[1661503215,\"1.9352012342611191\"],[1661503230,\"1.9333333333356961\"],[1661503245,\"1.914285714284203\"],[1661503260,\"1.9809523809540486\"],[1661503275,\"1.9809523809505847\"],[1661503290,\"2.000000000002089\"],[1661503305,\"1.971428571428302\"],[1661503320,\"1.9886093068440691\"],[1661503335,\"1.9809523809505847\"]]},{\"metric\":{\"instance\":\"192.168.124.13:9100\"},\"values\":[[1661502885,\"3.904761904767462\"],[1661502900,\"3.9523809523823394\"],[1661502915,\"3.8285714285753114\"],[1661502930,\"3.904761904767462\"],[1661502945,\"3.6571428571395392\"],[1661502960,\"3.628571428569216\"],[1661502975,\"3.5523809523770766\"],[1661502990,\"3.6571428571395392\"],[1661503005,\"3.609523809517712\"],[1661503020,\"3.6857142857098513\"],[1661503035,\"3.5904761904731464\"],[1661503050,\"3.8952380952417043\"],[1661503065,\"3.904761904760534\"],[1661503080,\"3.8857142857159466\"],[1661503095,\"3.7923087179322246\"],[1661503110,\"3.838095238101069\"],[1661503125,\"3.773261461692534\"],[1661503140,\"3.7999999999980605\"],[1661503155,\"3.754214205438988\"],[1661503170,\"3.847619047619888\"],[1661503185,\"3.9333333333308462\"],[1661503200,\"4.0076953846731715\"],[1661503215,\"4.038095238093298\"],[1661503230,\"4.0934113030682955\"],[1661503245,\"4.0761904761893675\"],[1661503260,\"4.064839330276849\"],[1661503275,\"4.019047619048733\"],[1661503290,\"3.9809523809526626\"],[1661503305,\"3.9474310747101615\"],[1661503320,\"3.8666666666644534\"],[1661503335,\"3.819047619049565\"]]}]}}";
        JSONObject jsonObject = JSON.parseObject(a, JSONObject.class);
        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("result").getJSONObject(0).getJSONArray("values");
        System.out.println(jsonArray.getJSONArray(0).getDouble(1));
    }

    @Test
    public void test8(){

        TestEnum a = TestEnum.valueOf("Abig");
        System.out.println(a.getName());
    }
}
