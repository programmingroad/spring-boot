package com.example.resttemplate.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author: liubq
 * @create: 2020/12/01 14:58
 * @description:
 **/

@Data
public class ProjectReq {

    @JsonProperty("project_name")
    private String projectName;

}
