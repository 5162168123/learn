package cn.meng;

public interface TestInterface {
    TestInterface DEFAULT = new TestImpl();

    void test();
}
