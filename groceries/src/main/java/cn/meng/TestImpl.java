package cn.meng;

public class TestImpl implements TestInterface{
    public TestImpl(){
        System.out.println("impl实例化");
    }

    @Override
    public void test() {
        System.out.println("test");
    }
}
