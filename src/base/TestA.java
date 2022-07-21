package base;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class TestA {
    public Test change(Test a,int b){
        a.setA(b);
        return a;
    }
    public static void main(String[] args) {
//        String a = "/a/b/c/d/e/f";
//        System.out.println(Arrays.toString(a.split("/",3)));
//        long time = 1644596665616L;
//        Date date = new Date(time);
//        System.out.println(date);
//        SimpleDateFormat sd = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
//        String format = sd.format(date);
//        System.out.println(format);
        TestA test = new TestA();
        ArrayList<Test> a = new ArrayList<>();
        a.add(new Test(1));
        a.add(new Test(2));

        for (Test test1 : a) {
            test.change(test1, 222);
        }
        System.out.println(a);

    }
}
class Test{
    int a;
    int b;

    public Test(int a) {
        this.a = a;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "Test{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
