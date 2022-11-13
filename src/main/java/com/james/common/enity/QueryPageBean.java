package com.james.common.enity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ACT
 * 封装查询条件
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryPageBean implements Serializable {
    /**
     * 页码
     */
    private Integer currentPage;
    /**
     * 每页记录数
     */
    private Integer pageSize;
    /**
     * 查询条件
     */
    private String queryString;
}
