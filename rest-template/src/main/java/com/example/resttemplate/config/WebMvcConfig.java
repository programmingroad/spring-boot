//package com.example.resttemplate.config;
//
//import com.example.resttemplate.interceptor.LogInterceptor;
//import com.example.resttemplate.interceptor.LoginInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @author: liubq
// * @create: 2020/10/13 11:14
// * @description:
// **/
//
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    private final LoginInterceptor loginInterceptor;
//
//    private final LogInterceptor logInterceptor;
//
//    public WebMvcConfig(LoginInterceptor loginInterceptor, LogInterceptor logInterceptor) {
//        this.loginInterceptor = loginInterceptor;
//        this.logInterceptor = logInterceptor;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
////        registry.addInterceptor(logInterceptor).addPathPatterns("/**");
////        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/test");
//    }
//}
