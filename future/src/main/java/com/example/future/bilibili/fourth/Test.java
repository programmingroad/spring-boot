package com.example.future.bilibili.fourth;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author: liubq
 * @create: 2020/11/27 14:31
 * @description:
 **/

@Slf4j
public class Test {

    public static void main(String[] args) {
        Lock lock = new Lock();
        Lock lock1 = new Lock();
        new Thread(() -> {
            lock.test1();
        }, "t1").start();
        new Thread(() -> {
            lock1.test2();
        }, "t2").start();
    }


}


@Slf4j
class Lock {
    private static Object lock = new Object();

    public void test1() {
        Integer
        synchronized (lock) {
            log.info("test1 start");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("test1 end");
        }
    }

    public synchronized void test2() {
        synchronized (lock) {
            log.info("test2 start");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("test2 end");
        }
    }

    public Object getLock() {
        return lock;
    }
}
