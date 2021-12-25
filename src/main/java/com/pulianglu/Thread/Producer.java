package com.pulianglu.Thread;

import java.util.Queue;
import java.util.Random;

public class Producer implements Runnable {

    //首先会有资源类
    private Queue<Product> queue;
    private int maxCapacity;

    public Producer(Queue<Product> queue, int maxCapacity) {
        this.queue = queue;
        this.maxCapacity = maxCapacity;
    }

    @Override
    public void run() {
        while (true){
            synchronized (queue) {
                while (queue.size() == maxCapacity) {
                    try {
                        System.out.println("生产者:" + Thread.currentThread().getName() + "等待中...Queue已达到最大值," +
                                "无法继续生产");
                        //queue.notifyAll();
                        queue.wait();
                        System.out.println("生产者" + Thread.currentThread().getName() + "退出等待");
                        //Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (queue.size() == 0) { //此时等于0,后面会生产用一个,即可通知消费者消费
                        queue.notifyAll();
                        System.out.println("生产者" + Thread.currentThread().getName() + "唤醒了所有线程");
                    }

                }

                Random random = new Random();
                Integer i = random.nextInt();
                queue.offer(new Product("产品" + i.toString()));
                System.out.println("生产者" + Thread.currentThread().getName() + "生产了产品：" + i.toString());
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }

    }
}

