package com.example.observer;

import org.springframework.context.ApplicationEvent;

/**
 * @author: programmingroad
 * @create: 2020/02/20 17:06
 * @description:
 **/
public class CustomEvent extends ApplicationEvent {
    public CustomEvent(Object object) {
        super(object);
    }
}
