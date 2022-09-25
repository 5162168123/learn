package cn.meng;

public class ExtendTest {


    public static void main(String[] args) {
        S s = new S();
        System.out.println(s.getType());
    }

}

class F {
    public static final int a = 1;
    int type;

}

class S extends F{
    int type = F.a;
    public int getType(){
        return type;
    }
}