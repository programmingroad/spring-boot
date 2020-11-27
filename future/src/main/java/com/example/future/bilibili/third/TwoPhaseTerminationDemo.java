package com.example.future.bilibili.third;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author: liubq
 * @create: 2020/11/24 17:58
 * @description: 两阶段终止模式
 * <p>
 * 在一个线程T1中如何【优雅】的终止线程T2，这里的【优雅】指的是给T2一个料理后事的机会
 **/
public class TwoPhaseTerminationDemo {

    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination twoPhaseTermination = new TwoPhaseTermination();
        twoPhaseTermination.start();
        TimeUnit.SECONDS.sleep(4);
        twoPhaseTermination.stop();
    }
}

@Slf4j
class TwoPhaseTermination {
    private Thread monitor;

    public void start() {
        monitor = new Thread(() -> {
            while (true) {
                Thread currentThread = Thread.currentThread();
                if (currentThread.isInterrupted()) {
                    log.info("料理后事");
                    break;
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                    log.info("执行监控记录");
                } catch (InterruptedException e) {
                    log.info("重新设置打断标记之前interrupted={}", currentThread.isInterrupted());
                    currentThread.interrupt();
                    log.info("重新设置打断标记之后interrupted={}", currentThread.isInterrupted());
                    e.printStackTrace();
                }
            }
        }, "monitor");
        monitor.start();
    }

    public void stop() {
        monitor.interrupt();
    }

}
