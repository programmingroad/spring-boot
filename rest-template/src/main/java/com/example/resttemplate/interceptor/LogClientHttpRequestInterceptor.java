package com.example.resttemplate.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author: liubq
 * @create: 2020/11/19 15:42
 * @description:
 **/

@Slf4j
@Component
public class LogClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        log.info("restTemplate request: uri={}, content-type={}, method={}, body={}", request.getURI(), request.getHeaders().getContentType(), request.getMethodValue(),
                new String(body));
        return execution.execute(request, body);
    }
}
