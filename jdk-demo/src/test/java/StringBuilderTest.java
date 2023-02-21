import org.junit.Test;

import java.util.UUID;

public class StringBuilderTest {
    @Test
    public void test1(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(1);
        stringBuilder.append(2);
        stringBuilder.append(3);
        stringBuilder.append(4);
        stringBuilder.append(5);
        stringBuilder.append(6);
        stringBuilder.append(7);
        stringBuilder.delete(2,6);
        System.out.println(stringBuilder.toString());
    }
    @Test
    public void test2(){
        Integer a = 3;
        int b = 7;
        System.out.println(a^b);
        System.out.println(b^a);
        int h;
        System.out.println(b == 0 ? 0:(h = a.hashCode()) ^ (h >>> 16));
        int c =  (h = a.hashCode()) ^ (h >>> 16);
    }

    @Test
    public void test3(){
        class Node{
            int a;
            int b;
            Node node;
            public Node(int a,int b,Node node){
                this.a = a;
                this.b = b;
                this.node = node;
            }

            @Override
            public String toString() {
                return "Node{" +
                        "a=" + a +
                        ", b=" + b +
                        ", node=" + node +
                        '}';
            }
        }

        Node a = new Node(1,1,null);
        Node b = new Node(2,2,a);
        Node c = a.node;
        a.node = new Node(3,3,null);
        System.out.println(c);
    }

}
