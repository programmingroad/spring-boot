package com.example.mybatis.api;

import com.example.mybatis.entity.City;
import com.example.mybatis.mapper.CityMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: liubq
 * @create: 2020/10/26 17:09
 * @description:
 **/

@RestController
public class CityApi {

    @Resource
    private CityMapper cityMapper;

    @GetMapping("find")
    public Object find(Integer pageNum, Integer pageSize) {
        PageInfo<City> cities = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> cityMapper.findAll());
        return cities;
    }
}
