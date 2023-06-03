package cn.meng;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class InterfaceTest {
    @Test
    public void test01(){
       TestInterface.DEFAULT.test();
    }

    @Test
    public void test02(){
        for (int i = 0; i < 100; i++) {
            System.out.println(i & 7);
        }
    }
}
