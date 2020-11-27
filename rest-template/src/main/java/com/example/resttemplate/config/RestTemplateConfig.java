package com.example.resttemplate.config;

import com.example.resttemplate.handler.RestTemplateErrorHandler;
import com.example.resttemplate.interceptor.LogClientHttpRequestInterceptor;
import com.example.resttemplate.interceptor.LogInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author: liubq
 * @create: 2020/09/07 18:11
 * @description:
 **/
@Configuration
public class RestTemplateConfig {

    @Value("${httpclient.soTimeout}")
    private int soTimeout;

    @Value("${httpclient.conTimeout}")
    private int conTimeout;

    private final RestTemplateErrorHandler restTemplateErrorHandler;

    private final LogClientHttpRequestInterceptor logClientHttpRequestInterceptor;

    public RestTemplateConfig(RestTemplateErrorHandler restTemplateErrorHandler, LogClientHttpRequestInterceptor logClientHttpRequestInterceptor) {
        this.restTemplateErrorHandler = restTemplateErrorHandler;
        this.logClientHttpRequestInterceptor = logClientHttpRequestInterceptor;
    }

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.setErrorHandler(restTemplateErrorHandler);
        restTemplate.setInterceptors(Collections.singletonList(logClientHttpRequestInterceptor));
        return restTemplate;
    }

    @Bean
    public ClientHttpRequestFactory factory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(soTimeout);
        factory.setConnectTimeout(conTimeout);
        return factory;
    }
}
