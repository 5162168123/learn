package cn.meng;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueDemo {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<String> linkedQueue = new ConcurrentLinkedQueue<>();
        linkedQueue.offer("zhangsan");

    }
}
