package base;

import javax.crypto.spec.PSource;

/**
 * 完全抽象
 *
 * 接口中只有常量和抽象方法,public abstract final可省略
 *
 * 实现接口需要实现所有方法
 */
public class InterfaceTest01 {
    public static void main(String[] args) {
        System.out.println(MyMath.PI);
//        MyMath.PI = 3.14;
    }
}

interface A{
    void mm();
}
interface B extends A{

}

interface C extends B,A{

}

interface MyMath{
    public static final double PI = 3.1415926;
    public abstract int sum(int a,int b);
}

class Sum implements MyMath{

    @Override
    public int sum(int a, int b) {
        return a+b;
    }
}