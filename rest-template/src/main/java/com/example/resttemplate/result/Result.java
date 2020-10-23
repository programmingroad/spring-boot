package com.example.resttemplate.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: liubq
 * @create: 2020/09/07 20:31
 * @description:
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result<T> {

    private int status;

    private String message;

    private T data;

    private PageInfo pageInfo;

}
