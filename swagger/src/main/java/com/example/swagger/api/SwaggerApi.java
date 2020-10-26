package com.example.swagger.api;

import com.example.swagger.dto.SwaggerDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author: liubq
 * @create: 2020/10/26 14:31
 * @description:
 **/

@RestController
public class SwaggerApi {

    @ApiOperation(value = "test",notes = "notes")
    @PostMapping(value = "/test")
    public SwaggerDTO getHarborProjects(@RequestBody SwaggerDTO swaggerDTO) {
        return swaggerDTO;
    }
}
