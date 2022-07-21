package cn.meng;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        App app = new App();
        new Thread(app::test);
    }
    public void test(){
        System.out.println(123);
    }
}
