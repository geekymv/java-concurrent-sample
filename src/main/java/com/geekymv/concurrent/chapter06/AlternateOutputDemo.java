package com.geekymv.concurrent.chapter06;

/**
 * 两个线程交替输出
 * A1B2C3D4E5F6G7
 */
public class AlternateOutputDemo {

    private static Object lock = new Object();

    public static void main(String[] args) {

        char[] aArr = "ABCDEFG".toCharArray();
        char[] cArr = "1234567".toCharArray();


        Thread t1 = new Thread(()-> {
            print(aArr);
        }, "t1");

        Thread t2 = new Thread(()-> {
            print(cArr);
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

    private static void print(char[] c) {
        synchronized (lock) {
            for(int i = 0, len = c.length; i < len; i++ ) {
                System.out.print(c[i]);

                lock.notify();

                if(i != len-1) {
                    // 最后一次输出之后无需再等待
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


}
