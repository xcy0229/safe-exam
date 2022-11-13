package com.james.common.enity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author ACT
 * 分页结果封装对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult implements Serializable {
    /**
     * 总记录数
     */
    private Long total;
    /**
     * 当前页结果
     */
    private List<?> rows;
}
