package com.example.leetcode.concurrency.order.print;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: liubq
 * @create: 2020/10/29 10:38
 * @description:
 **/

@Slf4j
public class Main {
    public static void main(String[] args) {
        Foo foo = new Foo();
        List<Integer> sourceList = Arrays.asList(1, 2, 3);
        Map<Integer, Runnable> map = new HashMap<>(3);
        map.put(1, () -> foo.first(() -> log.info("first")));
        map.put(2, () -> foo.second(() -> log.info("second")));
        map.put(3, () -> foo.third(() -> log.info("third")));
        List<Thread> threadList = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < sourceList.size(); i++) {
            Thread thread = new Thread(map.get(sourceList.get(i)), i == 0 ? "a" : (i == 1 ? "b" : "c"));
            thread.start();
            threadList.add(thread);
        }
        // 等待线程全部执行完毕 统计时间
        threadList.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        log.info("foo total cost time: {}ms", System.currentTimeMillis() - start);

    }
}

class Foo {
    /**
     * Atomic原子操作就是volatile + cas,. 在这里同时只会有一个线程去改变这个值, 所以cas可以不用, 可以直接使用volatile
     */
    private AtomicInteger firstJobDone = new AtomicInteger(0);
    private AtomicInteger secondJobDone = new AtomicInteger(0);

    public Foo() {
    }

    public void first(Runnable printFirst) {
        // printFirst.run() outputs "first".
        printFirst.run();
        // mark the first job as done, by increasing its count.
        firstJobDone.incrementAndGet();
    }

    public void second(Runnable printSecond) {
        while (firstJobDone.get() != 1) {
            // waiting for the first job to be done.
        }
        // printSecond.run() outputs "second".
        printSecond.run();
        // mark the second as done, by increasing its count.
        secondJobDone.incrementAndGet();
    }

    public void third(Runnable printThird) {
        while (secondJobDone.get() != 1) {
            // waiting for the second job to be done.
        }
        // printThird.run() outputs "third".
        printThird.run();
    }
}
