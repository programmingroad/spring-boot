package com.example.resttemplate.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author: liubq
 * @create: 2020/09/07 17:19
 * @description:
 **/

@Data
public class ProjectMetadata {

    /**
     * Whether content trust is enabled or not. If it is enabled, user can't pull unsigned images from this project. The valid values are "true", "false".
     */
    @JsonProperty("enable_content_trust")
    private String enableContentTrust;

    /**
     * Whether scan images automatically when pushing. The valid values are "true", "false".
     */
    @JsonProperty("auto_scan")
    private String autoScan;

    /**
     * If the vulnerability is high than severity defined here, the images can't be pulled. The valid values are "none", "low", "medium", "high", "critical".
     */
    private String severity;

    @JsonProperty("reuse_sys_cve_whitelist")
    private String reuseSysCveWhitelist;

    /**
     * The public status of the project. The valid values are "true", "false".
     */
    @JsonProperty("public")
    @JSONField(name = "public")
    private String isPublic;

    /**
     * Whether prevent the vulnerable images from running. The valid values are "true", "false".
     */
    @JsonProperty("prevent_vul")
    private String preventVul;
}
