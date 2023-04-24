package cn.meng;

public class CPUCacheTest {
//    protected Long l1,l2,l3,l4,l5,l6,l7;
    protected  final static Long a = 1L;
//    protected Long l8,l9,l10,l11,l12,l13,l14;
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            if(a == 1){

            }
        }
        System.out.println(System.currentTimeMillis()-start);
    }
}
