//package com.example.resttemplate.config;
//
//import com.example.resttemplate.filter.AssetFilter;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author: liubq
// * @create: 2020/11/20 09:54
// * @description:
// **/
//@Configuration
//public class FilterConfig {
//
//    private final AssetFilter assetFilter;
//
//    public FilterConfig(AssetFilter assetFilter) {
//        this.assetFilter = assetFilter;
//    }
//
////    @Bean
////    public FilterRegistrationBean filterRegistrationBean() {
////        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
////        filterRegistrationBean.setFilter(this.assetFilter);
////        filterRegistrationBean.addUrlPatterns("/*");
////        return filterRegistrationBean;
////    }
//}
