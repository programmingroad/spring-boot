package com.example.resttemplate.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: liubq
 * @create: 2020/09/07 17:18
 * @description:
 **/

@Data
public class Project {

    /**
     * The update time of the project.
     */
    @JsonProperty("update_time")
    private String updateTime;

    /**
     * The owner name of the project.
     */
    @JsonProperty("owner_name")
    private String ownerName;

    /**
     * The name of the project.
     */
    private String name;

    /**
     * A deletion mark of the project.
     */
    private Boolean deleted;

    /**
     * The owner ID of the project always means the creator of the project.
     */
    @JsonProperty("owner_id")
    private Integer ownerId;

    /**
     * The number of the repositories under this project.
     */
    @JsonProperty("repo_count")
    private Integer repoCount;

    /**
     * The creation time of the project.
     */
    @JsonProperty("creation_time")
    private String creationTime;

    /**
     * Correspond to the UI about whether the project's publicity is updatable (for UI)
     */
    private Boolean togglable;

    /**
     * Project ID
     */
    @JsonProperty("project_id")
    private Integer projectId;

    /**
     * The role ID with highest permission of the current user who triggered the API (for UI). This attribute is deprecated and will be removed in future versions.
     */
    @JsonProperty("current_user_role_id")
    private Integer currentUserRoleId;

    /**
     * The list of role ID of the current user who triggered the API (for UI)
     */
    @JsonProperty("current_user_role_ids")
    private List<Integer> currentUserRoleIds;

    /**
     * The total number of charts under this project.
     */
    @JsonProperty("chart_count")
    private Integer chartCount;

    @JsonProperty("cve_whitelist")
    private CVEWhitelist cveWhitelist;

    private ProjectMetadata metadata;

}
