public class GenericTest03 {
    public  void doSome(Object a){
        System.out.println(a);
    }

    public static void main(String[] args) {
        GenericTest03 a = new GenericTest03();
        a.doSome("111");
        a.doSome(111);
    }
}
