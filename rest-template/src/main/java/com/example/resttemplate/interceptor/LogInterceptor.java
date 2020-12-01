package com.example.resttemplate.interceptor;

import com.alibaba.fastjson.JSON;
import com.example.resttemplate.filter.AssetFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author: liubq
 * @create: 2020/11/18 15:20
 * @description:
 **/

@Component
@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        AssetFilter.AssetHttpServletRequestWrapper assetHttpServletRequestWrapper = (AssetFilter.AssetHttpServletRequestWrapper) request;
        Map<String, String[]> parameterMap = assetHttpServletRequestWrapper.getParameterMap();
        log.info("request: uri={}, content-type={}, method={}, params={}, body={}", assetHttpServletRequestWrapper.getRequestURI(),
                assetHttpServletRequestWrapper.getContentType(), assetHttpServletRequestWrapper.getMethod(), JSON.toJSONString(parameterMap),
                assetHttpServletRequestWrapper.getBody());
        return true;
    }
}
