package com.example.resttemplate.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.TeeOutputStream;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author: liubq
 * @create: 2020/11/20 09:32
 * @description: 由于 request/response.getInputStream只能读取一次 故重写 getInputStream 重新设置inputStream
 **/
@Slf4j
//@Component
public class AssetFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        AssetHttpServletRequestWrapper assetHttpServletRequestWrapper = new AssetHttpServletRequestWrapper((HttpServletRequest) request);
        AssetHttpServletResponseWrapper assetHttpServletResponseWrapper = new AssetHttpServletResponseWrapper((HttpServletResponse) response);
        chain.doFilter(assetHttpServletRequestWrapper, assetHttpServletResponseWrapper);
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
                public int read() throws IOException {
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

    public class AssetHttpServletResponseWrapper extends HttpServletResponseWrapper {

        private String body;

        /**
         * Constructs a response adaptor wrapping the given response.
         *
         * @param response The response to be wrapped
         * @throws IllegalArgumentException if the response is null
         */
        public AssetHttpServletResponseWrapper(HttpServletResponse response) throws IOException {
            super(response);
            final ServletOutputStream outputStream = response.getOutputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream() {
                @Override
                public void write(int b) {
                    try {
                        outputStream.write(b);
                    } catch (IOException e) {
                    }
                }
            };
            this.body = byteArrayOutputStream.toString();
        }

        public String getBody() {
            return this.body;
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(this.body.getBytes());
            return new ServletOutputStream() {

                @Override
                public void write(int b) throws IOException {
                    byteArrayOutputStream.write(b);
                }

                @Override
                public boolean isReady() {
                    return false;
                }

                @Override
                public void setWriteListener(WriteListener listener) {

                }
            };
        }
    }
}
