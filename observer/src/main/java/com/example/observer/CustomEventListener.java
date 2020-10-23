package com.example.observer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author: programmingroad
 * @create: 2020/02/20 17:38
 * @description:
 **/

@Component
@Slf4j
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent customEvent) {
        log.info("CustomEvent: {}", customEvent);
    }
}
