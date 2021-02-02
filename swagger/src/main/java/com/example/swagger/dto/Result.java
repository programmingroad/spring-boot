package com.example.swagger.dto;

import lombok.Data;

/**
 * @author: liubq
 * @create: 2020/12/30 17:57
 * @description:
 **/

@Data
public class Result<T> {

    private Integer code;

    private String message;

    private T data;
}
