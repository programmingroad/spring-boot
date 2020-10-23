package com.example.jwt.interceptor;

import com.example.jwt.constants.Constants;
import com.example.jwt.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: liubq
 * @create: 2020/10/13 11:07
 * @description:
 **/

@Component
@Slf4j
public class RestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(Constants.TOKEN);
        String username = JwtUtil.verify(token);
        // 验证通过,生成新的token
        String newToken = JwtUtil.sign(username);
        // 需要在response中设置expose-Headers供前端访问
        response.setHeader(Constants.TOKEN, newToken);
        return true;
    }
}
