package com.example.swagger.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author: liubq
 * @create: 2020/10/26 14:31
 * @description:
 **/

@Data
@ApiModel(value = "Swagger DTO template")
public class SwaggerDTO {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "name")
    private String name;

}
