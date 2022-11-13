package com.james.service;

import com.james.common.pojo.Collect;

import java.util.Map;

/**
 * @author ACT
 */
public interface CollectService {

    /**
     * 添加收藏题目
     * @param collect 信息
     * @return 是否成功
     */
    boolean add(Collect collect);

    /**
     * 查询所有的收藏
     * @param studentId 学生
     * @return 结果集
     */
    Map<String, Object> findAllCollect(Integer studentId);

    /**
     * 删除收藏题目
     * @param collect 信息
     */
    void remove(Collect collect);
}
