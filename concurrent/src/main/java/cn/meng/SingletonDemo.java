package cn.meng;

public class SingletonDemo {
    private SingletonDemo(){

    }

    private volatile static SingletonDemo singletonDemo;
    public SingletonDemo getInstance(){
        if(singletonDemo == null){
            synchronized (SingletonDemo.class){
                if(singletonDemo == null){
                    singletonDemo = new SingletonDemo();
                }
            }

        }
        return singletonDemo;
    }
}
