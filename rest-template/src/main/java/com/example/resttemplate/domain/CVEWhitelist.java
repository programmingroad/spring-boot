package com.example.resttemplate.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: liubq
 * @create: 2020/09/07 17:35
 * @description:
 **/

@Data
public class CVEWhitelist {

    /**
     * ID of the whitelist
     */
    private Integer id;

    /**
     * ID of the project which the whitelist belongs to. For system level whitelist this attribute is zero.
     */
    @JsonProperty("project_id")
    private Integer projectId;

    /**
     * 	ID of the whitelist
     */
    @JsonProperty("expires_at")
    private Integer expiresAt;

    private List<CVEWhitelistItem> items;
}
