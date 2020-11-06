package com.example.leetcode.concurrency.order.print;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author: liubq
 * @create: 2020/10/30 11:41
 * @description:
 **/
@Slf4j
public class Main1 {
    public static void main(String[] args) {
        Foo1 foo1 = new Foo1();
        List<Integer> sourceList = Arrays.asList(1, 2, 3);
        Map<Integer, Runnable> map = new HashMap<>(3);
        map.put(1, () -> foo1.first(() -> log.info("first")));
        map.put(2, () -> foo1.second(() -> log.info("second")));
        map.put(3, () -> foo1.third(() -> log.info("third")));
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
        log.info("foo1 total cost time: {}ms", System.currentTimeMillis() - start);

    }
}

class Foo1 {

    private volatile Integer firstJobDone = 0;
    private volatile Integer secondJobDone = 0;

    public Foo1() {
    }

    public void first(Runnable printFirst) {
        // printFirst.run() outputs "first".
        printFirst.run();
        // mark the first job as done, by increasing its count.
        firstJobDone++;
    }

    public void second(Runnable printSecond) {
        while (firstJobDone != 1) {
            // waiting for the first job to be done.
        }
        // printSecond.run() outputs "second".
        printSecond.run();
        // mark the second as done, by increasing its count.
        secondJobDone++;
    }

    public void third(Runnable printThird) {
        while (secondJobDone != 1) {
            // waiting for the second job to be done.
        }
        // printThird.run() outputs "third".
        printThird.run();
    }
}
