package cn.meng;





import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

public class VUTest {
    public static void main(String[] args) {
        String json = "{\"results\":[{\"statement_id\":0,\"series\":[{\"name\":\"jmeter\",\"columns\":[\"time\",\"last\"],\"values\":[[1657509655000,null],[1657509660000,null],[1657509665000,null],[1657509670000,null],[1657509675000,null],[1657509680000,null],[1657509685000,null],[1657509690000,null],[1657509695000,null],[1657509700000,null],[1657509705000,null],[1657509710000,null],[1657509715000,null],[1657509720000,null],[1657509725000,null],[1657509730000,null],[1657509735000,null],[1657509740000,null],[1657509745000,null],[1657509750000,null],[1657509755000,0],[1657509760000,10],[1657509765000,10],[1657509770000,10],[1657509775000,10],[1657509780000,10],[1657509785000,10],[1657509790000,10],[1657509795000,10],[1657509800000,10],[1657509805000,10],[1657509810000,10],[1657509815000,10],[1657509820000,30],[1657509825000,30],[1657509830000,30],[1657509835000,30],[1657509840000,30],[1657509845000,30],[1657509850000,30],[1657509855000,30],[1657509860000,30],[1657509865000,30],[1657509870000,30],[1657509875000,30],[1657509880000,50],[1657509885000,50],[1657509890000,50],[1657509895000,50],[1657509900000,50],[1657509905000,50],[1657509910000,50],[1657509915000,50],[1657509920000,50],[1657509925000,50],[1657509930000,50],[1657509935000,50],[1657509940000,70],[1657509945000,70],[1657509950000,70],[1657509955000,70],[1657509960000,70],[1657509965000,70],[1657509970000,70],[1657509975000,70],[1657509980000,70],[1657509985000,70],[1657509990000,70],[1657509995000,70],[1657510000000,90],[1657510005000,90],[1657510010000,90],[1657510015000,90],[1657510020000,90],[1657510025000,90],[1657510030000,90],[1657510035000,90],[1657510040000,90],[1657510045000,90],[1657510050000,90],[1657510055000,90],[1657510060000,null],[1657510065000,null],[1657510070000,null],[1657510075000,null],[1657510080000,null],[1657510085000,null],[1657510090000,null],[1657510095000,null],[1657510100000,null],[1657510105000,null],[1657510110000,null],[1657510115000,null],[1657510120000,null],[1657510125000,null],[1657510130000,null],[1657510135000,null],[1657510140000,null],[1657510145000,null],[1657510150000,null],[1657510155000,null],[1657510160000,null],[1657510165000,null],[1657510170000,null],[1657510175000,null],[1657510180000,null],[1657510185000,null],[1657510190000,null],[1657510195000,null],[1657510200000,null],[1657510205000,null],[1657510210000,null],[1657510215000,null],[1657510220000,null],[1657510225000,null],[1657510230000,null],[1657510235000,null],[1657510240000,null]]}]}]}";
        JSONObject jsonObject = JSONObject.parseObject(json);
        ArrayList<JSONObject> results = jsonObject.getObject("results",ArrayList.class);
        ArrayList<JSONObject> series = results.get(0).getObject("series", ArrayList.class);
        JSONObject jsonObject1 = series.get(0);
        ArrayList<JSONArray> values = jsonObject1.getObject("values",ArrayList.class);
        LinkedList<JSONObject> VU = new LinkedList<>();
        Long formerTimeStamp = 0L;
        Integer formerCount = 0;
        JSONObject tempVU = new JSONObject();

        for (JSONArray value : values) {
            Integer usersCount = value.getInteger(1);
            if(usersCount == null){
                usersCount=0;
            }
            Long timestamp = value.getLong(0);

            if( usersCount == 0){
                if(formerCount != 0){
                    tempVU.put("end",formerTimeStamp);
                    VU.add(tempVU);
                    break;
                }
                continue;
            }

            if(formerCount != usersCount){

                if( usersCount > formerCount){
                    if(!tempVU.containsKey("vu")){
                        tempVU.put("start",timestamp);
                        tempVU.put("vu",usersCount);
                        formerCount = usersCount;
                        continue;
                    }
                    if(formerTimeStamp - tempVU.getLong("start") > 30*100){
                        tempVU.put("end",formerTimeStamp);
                        VU.add(tempVU);
                    }
                    System.out.println(tempVU);
                    tempVU = new JSONObject();
                    tempVU.put("vu",usersCount);
                    tempVU.put("start",timestamp);
                }
                else{
                    tempVU.put("end",formerTimeStamp);
                    VU.add(tempVU);
                    break;
                }
            }

            formerCount = usersCount;
            formerTimeStamp = timestamp;
        }
        System.out.println(VU);
    }
}
