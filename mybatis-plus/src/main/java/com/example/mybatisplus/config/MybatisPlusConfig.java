package com.example.mybatisplus.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author: baoqi.liu
 * @create: 2019/11/26 19:09
 * @description:
 * @version: 1.0
 **/

@EnableTransactionManagement
@Configuration
@MapperScan("com.example.mybatisplus.mapper")
public class MybatisPlusConfig {

    /**
     * 分页插件如果不配置 就会走默认rowBounds逻辑分页
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }

}
