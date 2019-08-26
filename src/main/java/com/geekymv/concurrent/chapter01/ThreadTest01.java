package com.geekymv.concurrent.chapter01;

public class ThreadTest01 {

    public static void main(String[] args) {
        Thread t1 = new MyThread();
        // start thread
        t1.start();

        System.out.println("main方法所在线程名称 = " + Thread.currentThread().getName());
    }

}


class MyThread extends Thread {

    public MyThread() {
    }

    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("执行业务逻辑，当前线程名称 = " + Thread.currentThread().getName());
    }
}
