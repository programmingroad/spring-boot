package com.example.https;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: liubq
 * @create: 2021/2/21 6:31 下午
 * @description:
 **/

@RestController
public class TestApi {

    @GetMapping("test1")
    public Map<String, Object> test1() {
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "ok");
        result.put("code", 1);
        return result;
    }

}
