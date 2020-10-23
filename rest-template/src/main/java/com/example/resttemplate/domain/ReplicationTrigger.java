package com.example.resttemplate.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: liubq
 * @create: 2020/09/08 17:27
 * @description:
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplicationTrigger {

    /**
     * The replication policy trigger type. The valid values are manual, event_based and scheduled
     */
    private String type;

    @JsonProperty("trigger_settings")
    private TriggerSettings triggerSettings;
}
