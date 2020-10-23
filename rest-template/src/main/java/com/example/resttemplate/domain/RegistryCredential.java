package com.example.resttemplate.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: liubq
 * @create: 2020/09/08 15:45
 * @description:
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistryCredential {

    /**
     * Access key, e.g. user name when credential type is 'basic'.
     */
    @JsonProperty("access_key")
    private String accessKey;

    /**
     * Access secret, e.g. password when credential type is 'basic'
     */
    @JsonProperty("access_secret")
    private String accessSecret;

    /**
     * Credential type, such as 'basic', 'oauth'.
     */
    private String type;

}
