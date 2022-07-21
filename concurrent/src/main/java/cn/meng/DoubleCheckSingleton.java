package cn.meng;

public class DoubleCheckSingleton {
    private volatile static DoubleCheckSingleton instance;

    private DoubleCheckSingleton(){

    }

    public static DoubleCheckSingleton getInstance(){
        if(instance == null){
            synchronized (DoubleCheckSingleton.class){
                if(instance == null){
                    instance = new DoubleCheckSingleton();
                }

            }

        }
        return instance;
    }

    public static void main(String[] args) {
        new Thread(() -> System.out.println(123));
    }
}
