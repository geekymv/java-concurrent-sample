package com.geekymv.concurrent.chapter06;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j
public class FuturaTaskDemo {

    public static void main(String[] args) {

        FutureTask task = new FutureTask(() -> {
            log.info("call method");
            return 100;
        });

        new Thread(task, "t1").start();
        log.info("thread start");

        try {
            // 获取执行结果
            Object o = task.get();
            log.info("task get {} ", o);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
