package com.geekymv.concurrent.lock;

public class MonitorSyncDemo {

    private static int counter = 0;

    public static void main(String[] args) {

        MonitorSyncDemo demo = new MonitorSyncDemo();

        demo.test();
    }

    public synchronized void test() {
        counter++;
        System.out.println("counter = " + counter);
    }

}
