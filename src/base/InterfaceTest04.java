package base;

/**
 * 继承、实现同时出现extend在前，implements在后
 */
public class InterfaceTest04 {
}

class Animal{

}

class Cat extends Animal implements Flyable{

    @Override
    public void fly() {

    }
}

interface Flyable{
    void fly();
}
