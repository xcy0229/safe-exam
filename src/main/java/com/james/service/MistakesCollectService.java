package com.james.service;

import com.james.common.domain.MisQuestion;

import java.util.Map;

/**
 * @author ACT
 */
public interface MistakesCollectService {

    /**
     * 添加错题集
     * @param misQuestion 错题本信息
     */
    void add(MisQuestion misQuestion);

    /**
     * 查询所有的错题
     * @param studentId 学生
     * @return 结果集
     */
    Map<String, Object> findAllCollect(Integer studentId);

}
