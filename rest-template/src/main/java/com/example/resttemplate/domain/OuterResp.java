package com.example.resttemplate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: liubq
 * @create: 2020/09/08 10:37
 * @description:
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OuterResp<T> {

    /**
     * total count
     */
    private Long totalCount;

    private List<T> list;
}
