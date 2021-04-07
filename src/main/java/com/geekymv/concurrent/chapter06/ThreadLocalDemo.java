package com.geekymv.concurrent.chapter06;

public class ThreadLocalDemo {

    public static void main(String[] args) {

        ThreadLocal<M> tl = new ThreadLocal<>();
        tl.set(new M());

        tl.remove();
    }

}

class M {

}