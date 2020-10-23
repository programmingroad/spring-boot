package com.example.future.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.*;

/**
 * @author: programmingroad
 * @create: 2020/01/04 16:38
 * @description:
 **/

@Configuration
@EnableAsync
public class SpringAsyncConfig {

    /**
     * 配置 CompletableFuture 线程池
     *
     * @return
     */
    @Bean(name = "testThreadPool")
    public Executor taskLayerThreadPoolExecutor() {
        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("test-%d").build();
        return new ThreadPoolExecutor(10, 10, 1L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10), factory, new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
