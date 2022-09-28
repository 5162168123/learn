package cn.meng;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(10);
        queue.put("张三");
        queue.take();
        queue.size();

        Iterator<String> iterator = queue.iterator();
    }
}
