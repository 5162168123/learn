package cn.meng;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.junit.Test;
import org.w3c.dom.ls.LSOutput;
import sun.misc.Unsafe;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SynchronizedTest {
    private Integer i = 0;
    private ArrayList a;
    public SynchronizedTest(){
        this.a = new ArrayList<>();
    }

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        System.out.println(System.identityHashCode(linkedList));
        linkedList.add(new Object());
        linkedList.add(new Object());
        System.out.println(System.identityHashCode(linkedList));

    }

    static void a(){
        System.out.println(123);
    }

    @Test
    public void test(){
        int i =1;
        if((i=2) > 5){
            System.out.println(123);
        }
        else if ((i=3)>1){
            System.out.println(1233);
        }
    }

    public <T> T test(T a){
        Object aa = new Object();
        return (T)a;
    }

}

