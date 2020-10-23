package com.example.resttemplate.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author: liubq
 * @create: 2020/09/08 17:17
 * @description:
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplicationPolicy implements Serializable {

    /**
     * The policy ID.
     */
    private Integer id;

    /**
     * The policy name.
     */
    private String name;

    /**
     * The update time of the policy.
     */
    @JsonProperty("update_time")
    private String updateTime;

    /**
     * The description of the policy.
     */
    private String description;

    /**
     * Whether the policy is enabled or not.
     */
    private Boolean enabled;

    /**
     * The create time of the policy
     */
    @JsonProperty("creation_time")
    private String creationTime;

    /**
     * The destination namespace.
     */
    @JsonProperty("dest_namespace")
    private String destNamespace;

    /**
     * Whether to replicate the deletion operation.
     */
    private Boolean deletion;

    /**
     * Whether to override the resources on the destination registry.
     */
    private Boolean override;

    /**
     * The replication policy filter array.
     */
    private List<ReplicationFilter> filters;

    @JsonProperty("dest_registry")
    private Registry destRegistry;

    @JsonProperty("src_registry")
    private Registry srcRegistry;

    private ReplicationTrigger trigger;
}
