package com.geekymv.concurrent.chapter06;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockQueueDemo {


    public static void main(String[] args) {

        ReentrantLockQueue queue = new ReentrantLockQueue(10);

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


        new Thread(()-> {

            while (true) {
                int count = queue.getCount();
                System.out.println("count = " + count);
            }

        }).start();

    }
}

class ReentrantLockQueue<T> {

    // 容器容量
    private final int size;

    private LinkedList<T> list = new LinkedList<>();

    private int count;

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public ReentrantLockQueue(int size) {
        this.size = size;
    }

    public void put(T t) {
        lock.lock();
        try {
            while (count == size) {
                // 容器满了，生产者线程等待
                try {
                    producer.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 添加元素
            list.add(t);
            count++;

            // 通知消费者线程
            consumer.signalAll();

        }finally {
            lock.unlock();
        }
    }

    public T get() {
        // 获取锁
        lock.lock();
        try {
            while (count == 0) {
                // 容器内没有元素，消费者线程等待
                try {
                    consumer.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 移除第一个元素
            T t = list.removeFirst();
            count--;

            // 通知生产者线程
            producer.signalAll();

            return t;

        }finally {
            lock.unlock();
        }
    }

    public int getCount() {
        return count;
    }

}



