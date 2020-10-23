package com.example.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: programmingroad
 * @create: 2020/02/20 17:14
 * @description:
 **/

@RestController
@RequestMapping("/push")
public class DemoController {

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public String push(Object object) {
        publisher.publishEvent(new CustomEvent(object));
        return "事件发布成功";
    }
}
