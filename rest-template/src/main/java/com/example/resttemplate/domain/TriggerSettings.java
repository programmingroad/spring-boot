package com.example.resttemplate.domain;

import lombok.Data;

/**
 * @author: liubq
 * @create: 2020/09/08 17:28
 * @description:
 **/
@Data
public class TriggerSettings {

    /**
     * The cron string for scheduled trigger
     */
    private String cron;
}
