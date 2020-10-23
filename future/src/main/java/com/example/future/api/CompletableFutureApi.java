package com.example.future.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * @author: programmingroad
 * @create: 2020/01/04 16:40
 * @description:
 **/

@RestController
@Slf4j
public class CompletableFutureApi {

    @javax.annotation.Resource
    @Qualifier("testThreadPool")
    private Executor testExecutor;

    @GetMapping("/test")
    public Map<String, Object> callback() {
        // 可结合 DeferredResult 异步使用
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> log.info("test"), testExecutor);
        // 等待线程执行结束
        voidCompletableFuture.join();
        return Collections.singletonMap("code", 0);
    }

}
