package com.geekymv.concurrent.chapter04;

import org.junit.Test;

public class ThreadSimpleApi {

    @Test
    public void testThreadSimpleApi() {

        Thread t1 = new Thread(()-> {
            System.out.println("hello,world");
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");

        t1.start();

        System.out.println(t1.getId());
        System.out.println(t1.getName());
        // 不能依赖优先级来实现先后顺序执行
        System.out.println(t1.getPriority());
    }

}
