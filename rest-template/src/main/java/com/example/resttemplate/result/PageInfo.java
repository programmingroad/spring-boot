package com.example.resttemplate.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: liubq
 * @create: 2020/09/08 10:30
 * @description:
 **/
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo {

    private Integer currPageNum;

    private Integer pageSize;

    private Long totalCount;

    private Integer totalPageNum;

}
