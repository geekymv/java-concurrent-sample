package com.geekymv.concurrent.chapter06;

import java.util.LinkedList;
import java.util.Random;

public class WaitNotifyQueueDemo {

    public static void main(String[] args) {

        WaitNotifyQueue queue = new WaitNotifyQueue(10);

        Random random = new Random();

        for(int i = 0; i < 2; i++) {
            new Thread(()-> {
                while (true) {
                    queue.put(random.nextInt(100));
                }
            }, "生产者线程-" + i).start();
        }

        for(int i = 0; i < 10; i++) {
            new Thread(()-> {
                while (true) {
                    Object o = queue.get();
                    System.out.println(Thread.currentThread().getName() + " 数据 i = " + o);
                }
            }, "消费者线程-" + i).start();
        }

    }

}


/**
 * 写一个固定容量同步容器，拥有put和get方法，以及getCount方法，能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * @param <T>
 */
class WaitNotifyQueue<T> {

    // 容器容量
    private final int size;

    private LinkedList<T> list = new LinkedList<>();

    private int count;

    public WaitNotifyQueue(int size) {
        this.size = size;
    }

    public synchronized void put(T t) {

        while (count == size) {
            // 容器满了，生产者线程等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 添加元素
        list.add(t);
        count++;

        // 通知其他线程
        this.notifyAll();
    }

    public synchronized T get() {
        while (count == 0) {
            // 容器内没有元素，消费者线程等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 移除第一个元素
        T t = list.removeFirst();
        count--;

        // 通知其他线程
        this.notifyAll();

        return t;
    }

    public synchronized int getCount() {
        return count;
    }

}
