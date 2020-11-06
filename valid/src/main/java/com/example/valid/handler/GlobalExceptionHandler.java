package com.example.valid.handler;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author: liubq
 * @create: 2020/10/26 14:51
 * @description:
 **/

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("code", -1);
        map.put("message", e.getBindingResult().getFieldError().getDefaultMessage());
        return map;
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public Map<String, Object> handleConstraintViolationException(ConstraintViolationException e) {
        Map<String, Object> map = new HashMap<>(2);
        Iterator<ConstraintViolation<?>> iterator = e.getConstraintViolations().iterator();
        if (iterator.hasNext()) {
            map.put("message", iterator.next().getMessage());
        }
        map.put("code", -1);
        return map;
    }
}
