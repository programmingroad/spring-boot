package com.example.future.bilibili.third;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author: liubq
 * @create: 2020/11/24 14:57
 * @description:
 **/

@Slf4j
public class CreateThread {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                log.info("t1 running");
            }
        };

        Thread t2 = new Thread(() -> log.info("t2 running"), "t2");

        FutureTask futureTask = new FutureTask(() -> {
            log.info("t3 running");
            return null;
        });
        Thread t3 = new Thread(futureTask, "t3");

        t1.start();
        t2.start();
        t3.start();
        log.info("futureTask result={}", futureTask.get());
    }
}
