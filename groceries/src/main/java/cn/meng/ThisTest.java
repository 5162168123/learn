package cn.meng;

public class ThisTest {
}


class ThisA{
    Integer a;
    Integer b;

     public ThisA(int a,int b){
         this.a = a;
         this.b = b;
     }

     public void testThis(ThisB thisB){
         thisB.testThisB(this);
     }
}

class ThisB{
    public void testThisB(ThisA thisA){
        System.out.println(123);
    }
}
