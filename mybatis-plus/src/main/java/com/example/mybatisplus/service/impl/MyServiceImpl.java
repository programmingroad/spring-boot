package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.entity.User;
import com.example.mybatisplus.mapper.ArticleMapper;
import com.example.mybatisplus.mapper.ResultMapper;
import com.example.mybatisplus.mapper.UserMapper;
import com.example.mybatisplus.service.MyService;
import org.springframework.stereotype.Service;


/**
 * @author: programmingroad
 * @create: 2020/02/08 16:52
 * @description:
 **/
@Service
public class MyServiceImpl implements MyService {

    private final UserMapper userMapper;

    private final ArticleMapper articleMapper;

    private final ResultMapper resultMapper;

    public MyServiceImpl(UserMapper userMapper, ArticleMapper articleMapper, ResultMapper resultMapper) {
        this.userMapper = userMapper;
        this.articleMapper = articleMapper;
        this.resultMapper = resultMapper;
    }

    @Override
    public void test() {

//        List<User> users = userMapper.selectList(null);
//
//        System.out.println(users);

//        List<Article> articles = articleMapper.selectList(null);
//
//        System.out.println(articles);
//
//        List<Result> results = resultMapper.selectList(null);
//
//        System.out.println(results);

        IPage<User> userIPage = userMapper.selectPage(new Page<User>(1, 10), null);
        System.out.println(userIPage);

    }
}
