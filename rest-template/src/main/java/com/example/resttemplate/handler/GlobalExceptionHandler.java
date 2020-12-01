package com.example.resttemplate.handler;

import com.example.resttemplate.exception.HarborException;
import com.example.resttemplate.result.Result;
import com.example.resttemplate.result.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: liubq
 * @create: 2020/10/13 13:50
 * @description:
 **/

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("Exception={}", e);
        return ResultUtil.failed("internal error");
    }

    @ExceptionHandler(HarborException.class)
    public Result handleHarborException(HarborException e) {
        log.error("HarborException={}", e);
        return ResultUtil.failed(e.getMessage());
    }
}
