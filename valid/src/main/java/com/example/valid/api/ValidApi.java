package com.example.valid.api;

import com.example.valid.constant.Constants;
import com.example.valid.dto.ValidDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: liubq
 * @create: 2020/10/26 14:44
 * @description:
 **/

@RestController
public class ValidApi {

    @PostMapping(value = "/test1")
    public ValidDTO test1(@RequestBody @Valid ValidDTO validDTO) {
        return validDTO;
    }

    @GetMapping(value = "/test2")
    public Map<String, Object> test2(@RequestParam(required = false, defaultValue = Constants.PAGE) Integer page,
                                     @RequestParam(required = false, defaultValue = Constants.PAGE_SIZE) Integer pageSize) {
        Map<String, Object> result = new HashMap<>(2);
        result.put("page", page);
        result.put("pageSize", pageSize);
        return result;
    }

}
