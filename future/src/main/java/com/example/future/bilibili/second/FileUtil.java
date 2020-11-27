package com.example.future.bilibili.second;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: liubq
 * @create: 2020/11/24 13:59
 * @description:
 **/

@Slf4j
public class FileUtil {

    public static void read() {
        log.info("start read file");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("read file finish");
    }
}
