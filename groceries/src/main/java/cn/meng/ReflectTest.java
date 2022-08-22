package cn.meng;


import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

public class ReflectTest {
    public static void main(String[] args) throws IllegalAccessException {
        Test test = new Test("fielda",1);


        Field a = ReflectionUtils.findField(Test.class, "a");
        a.setAccessible(true);
        Object o = a.get(test);
        System.out.println(o);

    }


    static class Test{
        String a;
        int b;

        public Test(String a,int b){
            this.a = a;
            this.b = b;
        }
    }
}
