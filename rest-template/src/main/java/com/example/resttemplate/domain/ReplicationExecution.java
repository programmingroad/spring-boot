package com.example.resttemplate.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: liubq
 * @create: 2020/09/08 17:31
 * @description: The replication execution
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplicationExecution {

    /**
     * The policy ID
     */
    @JsonProperty("policy_id")
    private Integer policyId;

    /**
     * status eg: Succeed / InProgress
     */
    private String status;

}
