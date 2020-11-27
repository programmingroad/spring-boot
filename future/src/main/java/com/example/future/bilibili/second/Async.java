package com.example.future.bilibili.second;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: liubq
 * @create: 2020/11/24 13:59
 * @description:
 **/

@Slf4j
public class Async {

    public static void main(String[] args) {
        new Thread(() -> FileUtil.read()).start();
        log.info("do other things");
    }
}
