package com.example.resttemplate.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author: liubq
 * @create: 2020/11/20 09:32
 * @description: 由于 request/response.getInputStream只能读取一次 故重写 getInputStream 重新设置inputStream
 **/
@Slf4j
@Component
public class AssetFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        AssetHttpServletRequestWrapper assetHttpServletRequestWrapper = new AssetHttpServletRequestWrapper((HttpServletRequest) request);
        chain.doFilter(assetHttpServletRequestWrapper, response);
    }

    @Override
    public void destroy() {

    }

    public class AssetHttpServletRequestWrapper extends HttpServletRequestWrapper {

        private String body;

        /**
         * Constructs a request object wrapping the given request.
         *
         * @param request The request to wrap
         * @throws IllegalArgumentException if the request is null
         */
        public AssetHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
            super(request);
            this.body = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
        }

        public String getBody() {
            return this.body;
        }

        @Override
        public ServletInputStream getInputStream() {
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.body.getBytes());
            return new ServletInputStream() {

                @Override
                public int read() {
                    return byteArrayInputStream.read();
                }

                @Override
                public boolean isFinished() {
                    return false;
                }

                @Override
                public boolean isReady() {
                    return false;
                }

                @Override
                public void setReadListener(ReadListener listener) {
                }
            };
        }
    }
}
