package com.example.resttemplate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: liubq
 * @create: 2020/09/08 17:25
 * @description:
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplicationFilter {

    /**
     * The replication policy filter type.
     */
    private String type;

    /**
     * The value of replication policy filter.
     */
    private String value;
}
