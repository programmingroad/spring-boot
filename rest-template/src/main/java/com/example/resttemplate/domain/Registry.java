package com.example.resttemplate.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: liubq
 * @create: 2020/09/08 15:34
 * @description:
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Registry {

    /**
     * The registry ID.
     */
    private Integer id;

    /**
     * The registry URL string.
     */
    private String url;

    /**
     * The registry name
     */
    private String name;

    /**
     * Type of the registry, e.g. 'harbor'.
     */
    private String type;

    /**
     * Whether or not the certificate will be verified when Harbor tries to access the server
     */
    private Boolean insecure;

    /**
     * Description of the registry.
     */
    private String description;

    /**
     * Health status of the registry.
     */
    private String status;

    /**
     * The create time of the policy.
     */
    @JsonProperty("creation_time")
    private String creationTime;

    /**
     * The update time of the policy.
     */
    @JsonProperty("update_time")
    private String updateTime;

    private RegistryCredential credential;
}
