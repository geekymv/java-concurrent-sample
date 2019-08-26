package com.geekymv.concurrent.chapter03;

public class ThreadState02 {

    public static void main(String[] args) {
        Thread t = new Thread(new MyTask());
        t.start();
        System.out.println("线程状态 = " + t.getState());
    }

}
