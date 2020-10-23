package com.example.resttemplate.exception;

/**
 * @author: liubq
 * @create: 2020/09/22 17:28
 * @description:
 **/
public class HarborException extends RuntimeException {

    public HarborException(String message) {
        super("call harbor's api exception [" + message + "]");
    }
}
