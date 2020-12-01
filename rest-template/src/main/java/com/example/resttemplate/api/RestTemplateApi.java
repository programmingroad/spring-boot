package com.example.resttemplate.api;

import com.example.resttemplate.domain.ProjectReq;
import com.example.resttemplate.dto.ProjectDTO;
import com.example.resttemplate.result.Constants;
import com.example.resttemplate.result.Result;
import com.example.resttemplate.result.ResultUtil;
import com.example.resttemplate.service.RestTemplateApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: liubq
 * @create: 2020/11/17 17:16
 * @description:
 **/

@RestController
@Slf4j
public class RestTemplateApi {

    private final RestTemplateApiService restTemplateApiService;

    public RestTemplateApi(RestTemplateApiService restTemplateApiService) {
        this.restTemplateApiService = restTemplateApiService;
    }

    @GetMapping("/test")
    public Result<List<ProjectDTO>> test(@RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_NUM) Integer pageNum,
                                         @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_SIZE) Integer pageSize) {
        return restTemplateApiService.getProjects(pageNum, pageSize);
    }

    @GetMapping("/test1")
    public Result test1(@RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_NUM) Integer pageNum,
                        @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_SIZE) Integer pageSize) {
        return ResultUtil.success(null);
    }

    @PostMapping("/test2")
    public Result test2(@RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_NUM) Integer pageNum,
                        @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_SIZE) Integer pageSize) {
        return ResultUtil.success(null);
    }

    @PostMapping("test3")
    public Result<ProjectDTO> test3(@RequestBody ProjectDTO projectDTO) {
        log.info("projectDTO={}", projectDTO);
        return ResultUtil.success(projectDTO);
    }

    @PostMapping("test4")
    public Result test4(@RequestBody ProjectReq projectReq) {
        return restTemplateApiService.create(projectReq);
    }

    @GetMapping(value = "/test5/{projectName}/repositories/")
    public Result test5(@PathVariable("projectName") String projectName) {
        log.info("projectName={}", projectName);
        return ResultUtil.success(projectName);
    }
}
