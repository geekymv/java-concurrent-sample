package com.geekymv.concurrent.chapter04;

public class DaemonThread {

    public static void main(String[] args) {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("当前线程 " + Thread.currentThread().getName());
                try {
                    Thread.sleep(10_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("当前线程 " + Thread.currentThread().getName());
            }
        });
        // 设置成守护线程
        t.setDaemon(true);
        t.start();

        System.out.println("当前线程 " + Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
    }

}
