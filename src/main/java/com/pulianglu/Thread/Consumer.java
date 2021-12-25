package com.pulianglu.Thread;

import java.util.Queue;

public class Consumer implements Runnable {
    private Queue<Product> queue;
    private int maxCapacity;

    public Consumer(Queue<Product> queue, int maxCapacity) {
        this.queue = queue;
        this.maxCapacity = maxCapacity;
    }



    @Override
    public void run() {
        while (true){
            synchronized (queue) {
                while (queue.isEmpty()) {
                    try {
                        System.out.println("消费者" + Thread.currentThread().getName() + "等待中... Queue 已缺货，无法消费");
                        //queue.notifyAll();
                        queue.wait();
                        System.out.println("消费者" + Thread.currentThread().getName() + "退出等待");
                        //Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (queue.size() == maxCapacity) {  //消费一个,即可通知生产者生产
                    queue.notifyAll();
                    System.out.println("消费者" + Thread.currentThread().getName() + "唤醒了所有线程");
                }

                Product product = queue.poll();
                System.out.println("消费者" + Thread.currentThread().getName() + "消费了：" + product.getName());
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }

    }
}
