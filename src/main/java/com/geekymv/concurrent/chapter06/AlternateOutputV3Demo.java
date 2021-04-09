package com.geekymv.concurrent.chapter06;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程交替输出
 * A1B2C3D4E5F6G7
 */
public class AlternateOutputV3Demo {

    private static Lock lock = new ReentrantLock();

    private static Condition c1 = lock.newCondition();
    private static Condition c2 = lock.newCondition();


    public static void main(String[] args) {

        char[] aArr = "ABCDEFG".toCharArray();
        char[] cArr = "1234567".toCharArray();

        Thread t1 = new Thread(()-> {
            lock.lock();
            try {
                for(char c : aArr) {
                    System.out.print(c);
                    // 通知t2线程
                    c2.signal();
                    try {
                        // t1线程阻塞
                        c1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 通知t2线程，避免t2线程的最后一次等待无法唤醒
                c2.signal();

            }finally {
                lock.unlock();
            }

        }, "t1");

        Thread t2 = new Thread(()-> {
            lock.lock();
            try {

                for(char c : cArr) {
                    System.out.print(c);
                    c1.signal();
                    try {
                        c2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                c1.signal();

            }finally {
                lock.unlock();
            }

        }, "t2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
