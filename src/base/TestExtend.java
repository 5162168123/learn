package base;

import java.lang.reflect.Parameter;

public class TestExtend {
    public static void main(String[] args) {
        Child child = new Child();
        child.a();
    }
}



class Parent{
    public Parent(){
        System.out.println("弗雷构造");
    }

    void a(){
        System.out.println("弗雷方法a");
    }
}

class Child extends Parent {
    public Child(){
        System.out.println("子类构造");
    }

    @Override
    void a(){
        System.out.println("子类方法a");
    }
        }
