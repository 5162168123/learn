package base;

/**
 * 类之间共同特征提取出来，形成的抽象类。无法实例化
 * 抽象类不一定有抽象方法，抽象方法必须在抽象类中
 *
 * 抽象方法public abstract 修饰
 * 抽象方法被非抽象类继承必须重写
 */
public class AbstractTest01 {
    public static void main(String[] args) {
//        new Account();
    }
}

abstract class Account{
    public Account(String i){

    }
    public Account(){

    }
    public abstract void withdraw();


}

class CreditAccount extends Account{

    @Override
    public void withdraw() {

    }
}