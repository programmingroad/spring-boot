package com.example.resttemplate.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author: liubq
 * @create: 2020/09/07 17:35
 * @description:  The item in CVE whitelist
 **/
@Data
public class CVEWhitelistItem {

    /**
     * The ID of the CVE, such as "CVE-2019-10164"
     */
    @JsonProperty("cve_id")
    private String cveId;
}
