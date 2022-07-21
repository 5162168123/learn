package cn.meng;


import java.util.LinkedList;

public class ThreadUnsafeDemo {
    private static Integer data = 0;
    private static Integer data1 = 0;
    private static LinkedList<Integer> linkedList = new LinkedList<>();
    private static String date = new String("123");
    private static Object o = new Object();
    public ThreadUnsafeDemo(){

    }
    public static void main(String[] args) throws Exception{
        ThreadUnsafeDemo threadUnsafeDemo = new ThreadUnsafeDemo();
        Thread a = new Thread(() -> {


                    for (int i = 0; i < 50000; i++) {
                        synchronized (linkedList){
                            linkedList.add(i);
//                            System.out.println(Thread.currentThread().getName()+"++++++++++"+ ++data);
                    }
                }

        });

        Thread b = new Thread(() -> {

                    for (int i = 0; i < 50000; i++) {
                        synchronized (linkedList){
                            linkedList.add(i);
//                            System.out.println(Thread.currentThread().getName()+"++++++++++"+ ++data);
                    }
                }
        });

        a.start();
        b.start();
        a.join();
        b.join();

        System.out.println(linkedList.size());
    }

}
