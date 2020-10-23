package com.example.jwt.controller;

import com.example.jwt.constants.Constants;
import com.example.jwt.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: liubq
 * @create: 2020/10/13 09:29
 * @description:
 **/

@RestController
@Slf4j
public class JwtController {

    @GetMapping(value = "/login")
    public String login(HttpServletResponse response) {
        String token = JwtUtil.sign("username");
        response.setHeader(Constants.TOKEN, token);
        return "ok";
    }

    @GetMapping(value = "/test")
    public String test() {
        return "ok";
    }
}
