package cn.meng;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();
        list.add("zhagnsan");
        System.out.println(list);
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());
    }
}
