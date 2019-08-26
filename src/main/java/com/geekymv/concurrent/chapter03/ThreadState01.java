package com.geekymv.concurrent.chapter03;

public class ThreadState01 {

    public static void main(String[] args) {
        Thread t = new Thread(new MyTask());
        System.out.println("线程状态 = " + t.getState());
    }

}
