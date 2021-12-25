package com.pulianglu.Thread;

import java.util.ArrayDeque;
import java.util.Queue;

public class test {

    public static void main(String[] args) {
        Queue<Product> queue = new ArrayDeque<>();

        String producerLock = "producerLock";
        String consumerLock = "ConsumerLock";

        //for (int i = 0; i < 10; i++) {
            new Thread(new Producer(queue, 10)).start();
            new Thread(new Consumer(queue, 10)).start();
        new Thread(new Producer(queue, 10)).start();
        new Thread(new Consumer(queue, 10)).start();
        //}
    }
}
