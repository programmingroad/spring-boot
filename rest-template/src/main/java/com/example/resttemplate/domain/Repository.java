package com.example.resttemplate.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author: liubq
 * @create: 2020/09/08 9:28
 * @description:
 **/

@Data
public class Repository {

    /**
     * The update time of the repository
     */
    @JsonProperty("update_time")
    private String updateTime;

    /**
     * The description of the repository
     */
    private String description;

    /**
     * The count that the artifact inside the repository pulled
     */
    @JsonProperty("pull_count")
    private Integer pullCount;

    /**
     * The creation time of the repository
     */
    @JsonProperty("creation_time")
    private String creationTime;

    /**
     * The count of the artifacts inside the repository
     */
    @JsonProperty("artifact_count")
    private Integer artifactCount;

    /**
     * The ID of the project that the repository belongs to
     */
    @JsonProperty("project_id")
    private Integer projectId;

    /**
     * The ID of the repository
     */
    private Integer id;

    /**
     * The name of the repository
     */
    private String name;
}
