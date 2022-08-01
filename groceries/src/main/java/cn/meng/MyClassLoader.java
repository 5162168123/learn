package cn.meng;

public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException{
        Integer a = 1;
        if(a instanceof Integer){

        }
        return null;
    }
}
