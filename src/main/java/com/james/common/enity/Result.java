package com.james.common.enity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ACT
 * 封装返回结果
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {
    /**
     * 执行结果，true为执行成功 false为执行失败
     */
    private boolean flag;
    /**
     * 返回数据
     */
    private String message;
    /**
     * 返回结果信息
     */
    private Object data;

    public Result(boolean flag, String message) {
        super();
        this.flag = flag;
        this.message = message;
    }
}
