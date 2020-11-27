package com.example.future.bilibili.third;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author: liubq
 * @create: 2020/11/24 16:02
 * @description: <></>
 * 1. 调用 sleep 会让当前线程从 Running 进入 Timed Waiting 状态（阻塞）
 * 2. 其它线程可以使用 interrupt 方法打断正在睡眠的线程，这时 sleep 方法会抛出 InterruptedException
 * 3. 睡眠结束后的线程未必会立刻得到执行
 * 4. 建议用 TimeUnit 的 sleep 代替 Thread 的 sleep 来获得更好的可读性
 **/

@Slf4j
public class SleepDemo {

    public static void main(String[] args) throws InterruptedException {
//        test1();
        test2();
    }

    private static void test1() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        t1.start();
        log.info("t1 state={}", t1.getState());
        Thread.sleep(1000);
        log.info("t1 state={}", t1.getState());
    }

    private static void test2() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.info("enter sleep...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                log.info("wake up...");
            }
        }, "t1");
        t1.start();
        TimeUnit.SECONDS.sleep(1);
//        Thread.sleep(1000);
        log.info("interrupt...");
        t1.interrupt();

    }
}
