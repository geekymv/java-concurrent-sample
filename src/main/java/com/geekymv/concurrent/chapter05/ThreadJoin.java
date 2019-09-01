package com.geekymv.concurrent.chapter05;

import org.junit.Test;

public class ThreadJoin {

    @Test
    public void testThreadJoin() {
        Thread t1 = new Thread(()-> {
            for(int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " --> " + i);
            }
        });
        t1.start();

        try {
            // 当前线程是main线程，它等待 t1 线程执行完毕，才继续执行
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("线程 t1 执行完毕");

        // 执行 main 线程
        for(int i = 0; i < 100; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " --> " + i);
        }

    }


    @Test
    public void testThreadJoin2() {
        Thread t1 = new Thread(()-> {
            for(int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " --> " + i);
            }
        }, "t1");

        Thread t2 = new Thread(()-> {
            for(int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " --> " + i);
            }
        }, "t2");

        t1.start();
        t2.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // main 线程会等待 t1 线程和 t2 线程执行完毕后再执行，而 t1 和 t2 会交替执行。

        System.out.println("线程 t1 t2 执行完毕");

        for(int i = 0; i < 100; i++) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " --> " + i);
        }

    }

}
