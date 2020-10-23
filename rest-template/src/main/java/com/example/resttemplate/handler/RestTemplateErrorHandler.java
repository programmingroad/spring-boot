package com.example.resttemplate.handler;

import com.example.resttemplate.exception.HarborException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;
import java.net.URI;

/**
 * @author: liubq
 * @create: 2020/09/09 10:55
 * @description:
 **/
@Slf4j
@Component
public class RestTemplateErrorHandler extends DefaultResponseErrorHandler {

    @Value("${harbor.url}")
    private String harborApi;

    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
        String responseBody = new String(FileCopyUtils.copyToByteArray(response.getBody()));
        log.error("restTemplate request error response: statusCode={}, responseBody={}", response.getStatusCode(), responseBody);
        String urlStr = url.toString();
        if (urlStr.startsWith(this.harborApi)) {
            throw new HarborException(responseBody);
        }
    }
}
