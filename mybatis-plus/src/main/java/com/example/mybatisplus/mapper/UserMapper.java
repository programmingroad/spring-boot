package com.example.mybatisplus.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.entity.User;

/**
 * @author: programmingroad
 * @create: 2020/02/08 16:35
 * @description:
 **/

@DS("mysql-test1")
public interface UserMapper extends BaseMapper<User> {

}
