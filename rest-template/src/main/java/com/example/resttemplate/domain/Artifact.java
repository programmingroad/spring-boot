package com.example.resttemplate.domain;

import lombok.Data;

import java.util.List;

/**
 * @author: liubq
 * @create: 2020/09/08 10:00
 * @description:
 **/

@Data
public class Artifact {

    /**
     * The ID of the artifact
     */
    private Integer id;

    private List<Tag> tags;

}
