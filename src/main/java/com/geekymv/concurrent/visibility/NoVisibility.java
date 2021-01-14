package com.geekymv.concurrent.visibility;

import java.util.concurrent.TimeUnit;

public class NoVisibility {

    private static boolean retry = true;

    public static void main(String[] args) {
        new Thread(()-> {
            while (retry) {
            }

        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        retry = false;
        System.out.println("retry = " + retry);
    }





}
