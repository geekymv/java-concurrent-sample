package com.geekymv.concurrent.chapter02;

public class ThreadTest02 {

    public static void main(String[] args) {
        MyTask myThread02 = new MyTask();
        Thread t1 = new Thread(myThread02, "线程1");
        // start thread
        t1.start();

        System.out.println("main方法所在线程名称 = " + Thread.currentThread().getName());
    }

}


class MyTask implements Runnable {

    @Override
    public void run() {
        System.out.println("执行业务逻辑，当前线程名称 = " + Thread.currentThread().getName());
    }
}
