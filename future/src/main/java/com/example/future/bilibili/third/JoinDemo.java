package com.example.future.bilibili.third;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author: liubq
 * @create: 2020/11/24 16:37
 * @description:
 **/

@Slf4j
public class JoinDemo {

    static int r = 0;

    static int r1 = 0;

    static int r2 = 0;

    public static void main(String[] args) throws InterruptedException {
//        test1();
        test2();
    }

    private static void test1() throws InterruptedException {
        log.info("start");
        Thread t1 = new Thread(() -> {
            log.info("t1 start");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("t1 finish");
            r = 10;
        }, "t1");
        t1.start();
        t1.join();
        log.info("result={}", r);
        log.info("finish");
    }

    private static void test2() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r1 = 10;
        }, "t1");
        Thread t2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r2 = 20;
        }, "t2");

        t1.start();
        t2.start();
        long start = System.currentTimeMillis();
        log.info("begin join");
        t1.join();
        log.info("t1 join finish");
        t2.join();
        log.info("t2 join finish");
        long end = System.currentTimeMillis();
        log.info("r1={}, r2={}, cost={}", r1, r2, end - start);

    }
}
