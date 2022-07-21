package cn.meng;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JsonTest {
    public static void main(String[] args) {
        StringBuilder a = new StringBuilder();
        String json ="{\"results\":[{\"statement_id\":0,\"series\":[{\"name\":\"jmeter\",\"tags\":{\"transaction\":\"1.保存海报接口\"},\"columns\":[\"time\",\"sum\",\"sum_1\",\"sum_2\",\"sum_3\",\"sum_4\",\"sum_5\",\"sum_6\",\"sum_sum\"],\"values\":[[0,1155705,631.5207650273225,0.09871948087431698,0.1500748633879784,0.1888706284153003,0.25318193989071025,22,0.00001903599967119637]]},{\"name\":\"jmeter\",\"tags\":{\"transaction\":\"2.保存获客数据接口\"},\"columns\":[\"time\",\"sum\",\"sum_1\",\"sum_2\",\"sum_3\",\"sum_4\",\"sum_5\",\"sum_6\",\"sum_sum\"],\"values\":[[0,3869131,763.0923076923077,0.09793536489151886,0.16753461538461437,0.18354861932938896,0.2149420315581852,253,0.00006538936003976087]]},{\"name\":\"jmeter\",\"tags\":{\"transaction\":\"3.保存预约信息_生命活动二期\"},\"columns\":[\"time\",\"sum\",\"sum_1\",\"sum_2\",\"sum_3\",\"sum_4\",\"sum_5\",\"sum_6\",\"sum_sum\"],\"values\":[[0,2353909,651.2665734265735,0.06563825174825175,0.13450279720279668,0.14250727272727204,0.16363860139860176,25631,0.010888696207032643]]},{\"name\":\"jmeter\",\"tags\":{\"transaction\":\"4.抽奖\"},\"columns\":[\"time\",\"sum\",\"sum_1\",\"sum_2\",\"sum_3\",\"sum_4\",\"sum_5\",\"sum_6\",\"sum_sum\"],\"values\":[[0,595,7.485714285714286,0.1280997934595525,0.13842857142857143,0.14564285714285713,0.9829199999999965,333,0.5596638655462185]]}]}]}";
        JSONObject jsonObject = JSONObject.parseObject(json);
        ArrayList<JSONObject> results = jsonObject.getObject("results", ArrayList.class);
        ArrayList<JSONObject> series = results.get(0).getObject("series", ArrayList.class);
        Map<String,String> resultMap = new HashMap<>();
        for (JSONObject s : series) {
//            System.out.println(s.get("tags"));
            JSONObject tags = s.getJSONObject("tags");
//            System.out.println(tags.getString("transaction"));
//            System.out.println(tags.get("transaction"));
            JSONArray values = s.getJSONArray("values");
            values = (JSONArray) values.get(0);
            String totalRequest = ((Integer)values.get(1)).toString();
            String tps = ((BigDecimal) values.get(2)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
            String RT = ((BigDecimal) values.get(3)).setScale(3, BigDecimal.ROUND_HALF_UP).toString();
            String errorRate = ((BigDecimal) values.get(8)).movePointRight(2).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
            resultMap.put(tags.getString("transaction"),totalRequest+"\t"+tps+"\t"+RT+"\t"+errorRate);

//            System.out.println(totalRequest+"\t"+tps+"\t"+RT+"\t"+errorRate);
//            System.out.println(values.get(1)+"\t"+values.get(2)+"\t"+values.get(8));
        }
        for (String s1 : resultMap.keySet()) {
            System.out.println(s1+":"+resultMap.get(s1));
        }
        int ab = 0;
        System.out.println(ab==1?1:2);
    }
}
