package com.example.valid.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author: liubq
 * @create: 2020/10/26 14:43
 * @description:
 **/
@Data
public class ValidDTO {

    @NotEmpty(message = "id can't be empty")
    private String id;

    @NotEmpty(message = "name can't be empty")
    private String name;

}
