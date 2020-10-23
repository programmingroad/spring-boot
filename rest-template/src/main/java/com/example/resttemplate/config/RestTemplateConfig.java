package com.example.resttemplate.config;

import com.example.resttemplate.handler.RestTemplateErrorHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

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

    public RestTemplateConfig(RestTemplateErrorHandler restTemplateErrorHandler) {
        this.restTemplateErrorHandler = restTemplateErrorHandler;
    }

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.setErrorHandler(restTemplateErrorHandler);
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
