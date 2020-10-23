package com.example.resttemplate.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author: liubq
 * @create: 2020/09/08 10:01
 * @description:
 **/
@Data
public class Tag {

    /**
     * The ID of the repository that the tag belongs to
     */
    @JsonProperty("repository_id")
    private Integer repositoryId;

    /**
     * The name of the tag
     */
    private String name;

    /**
     * The push time of the tag
     */
    @JsonProperty("push_time")
    private String pushTime;

    /**
     * The latest pull time of the tag
     */
    @JsonProperty("pull_time")
    private String pullTime;

    /**
     * The attribute indicates whether the tag is signed or not
     */
    private Boolean signed;


    /**
     * The ID of the tag
     */
    private Integer id;

    /**
     * The immutable status of the tag
     */
    private Boolean immutable;

    /**
     * The ID of the artifact that the tag attached
     */
    @JsonProperty("artifact_id")
    private Integer artifactId;

}
