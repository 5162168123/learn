package base;

public class InterfaceTest03 {
}

interface AA{
    void m1();
}

interface BB{
    void m2();
}

interface CC{
    void m3();
}

class D implements AA,BB,CC{

    @Override
    public void m1() {

    }

    @Override
    public void m2() {

    }

    @Override
    public void m3() {

    }
}