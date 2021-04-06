package com.geekymv.concurrent.lock;

/**
 * javap -v target/classes/com/geekymv/concurrent/lock/MonitorDemo.class
 */
public class MonitorDemo {

    private static Object lock = new Object();

    private static int counter = 0;

    public static void main(String[] args) {

        synchronized (lock) {
            counter++;
        }

        System.out.println(counter);

    }

}
