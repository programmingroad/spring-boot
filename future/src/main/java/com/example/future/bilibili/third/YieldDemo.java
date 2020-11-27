package com.example.future.bilibili.third;

/**
 * @author: liubq
 * @create: 2020/11/24 16:16
 * @description: <></>
 * 1. 调用 yield 会让当前线程从 Running 进入 Runnable 就绪状态，然后调度执行其它线程
 * 2. 具体的实现依赖于操作系统的任务调度器
 **/
public class YieldDemo {

    public static void main(String[] args) {
        Thread.yield();
    }
}
