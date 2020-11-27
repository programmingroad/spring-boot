package com.example.future.bilibili.third;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @author: liubq
 * @create: 2020/11/27 10:59
 * @description:
 **/

@Slf4j
public class ParkDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("park...");
            // 只有打断标记为false的时候才生效
            LockSupport.park();
            log.debug("unpark...");
            // 不会清除打断状态
//            log.debug("打断状态={}", Thread.currentThread().isInterrupted());
            // 会清除打断状态
            log.debug("打断状态={}", Thread.interrupted());
            LockSupport.park();
            log.debug("unpark...");
        }, "t1");
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
    }
}
