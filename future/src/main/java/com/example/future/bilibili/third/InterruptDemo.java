package com.example.future.bilibili.third;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author: liubq
 * @create: 2020/11/24 17:25
 * @description:<></> 1. 打断阻塞的线程, 会清空打断状态，以 sleep 为例
 * 2. 打算正常运行的线程，不会清空打断状态且不会影响被打断的线程的运行
 **/

@Slf4j
public class InterruptDemo {

    public static void main(String[] args) throws InterruptedException {
//        test1();
        test2();
    }

    /**
     * 打断阻塞的线程, 会清空打断状态，以 sleep 为例
     *
     * @throws InterruptedException
     */
    private static void test1() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.info("sleep...");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        log.info("interrupt");
        t1.interrupt();
        log.info("interrupted={}", t1.isInterrupted());
    }

    /**
     * 打算正常运行的线程，不会清空打断状态且不会影响被打断的线程的运行
     *
     * @throws InterruptedException
     */
    private static void test2() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    log.info("interrupted");
                    break;
                }
            }
        }, "t1");
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        log.info("interrupt");
        t1.interrupt();
        log.info("interrupted={}", t1.isInterrupted());

    }
}
