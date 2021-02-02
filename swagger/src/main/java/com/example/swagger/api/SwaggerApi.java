package com.example.swagger.api;

import com.example.swagger.dto.Result;
import com.example.swagger.dto.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


/**
 * @author: liubq
 * @create: 2020/10/26 14:31
 * @description:
 **/

@RestController
@Slf4j
public class SwaggerApi {

    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user) {
        log.info("user={}", user);
        Result result = new Result();
        result.setCode(0);
        result.setMessage("success");
        return result;
    }

    @PostMapping(value = "/updateUsername",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Result updateUser(String name) {
        log.info("name={}", name);
        Result result = new Result();
        result.setCode(0);
        result.setMessage("success");
        return result;
    }

    @GetMapping("/getUser")
    public Result<User> getUser(Integer id) {
        log.info("id={}", id);
        User user = new User();
        user.setName("tom");
        Result<User> result = new Result();
        result.setCode(0);
        result.setMessage("success");
        result.setData(user);
        return result;
    }

    @GetMapping("/getUser/{id}")
    public Result<User> getUserById(@PathVariable Integer id) {
        log.info("id={}", id);
        User user = new User();
        user.setName("tom");
        Result<User> result = new Result();
        result.setCode(0);
        result.setMessage("success");
        result.setData(user);
        return result;
    }
}
