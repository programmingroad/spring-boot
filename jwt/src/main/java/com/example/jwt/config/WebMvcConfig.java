package com.example.jwt.config;

import com.example.jwt.constants.Constants;
import com.example.jwt.interceptor.RestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: liubq
 * @create: 2020/10/13 11:14
 * @description:
 **/

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final RestInterceptor restInterceptor;

    public WebMvcConfig(RestInterceptor restInterceptor) {
        this.restInterceptor = restInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(restInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/login"
                );
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.setAllowCredentials(true);
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.setMaxAge(3600L);
        // 需要expose header 否则前端取不到
        config.addExposedHeader(Constants.TOKEN);
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        return new CorsFilter(configSource);
    }

    /**
     * 配置了拦截器 下面配置不生效 需要采用上述filter
     *
     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**").exposedHeaders(Constants.TOKEN);
//    }
}
