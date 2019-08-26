package com.geekymv.concurrent.chapter03;

public class MyTask implements Runnable {
    @Override
    public void run() {
        System.out.println("执行业务逻辑，当前线程名称 = " + Thread.currentThread().getName());
    }
}