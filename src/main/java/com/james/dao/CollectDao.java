package com.james.dao;

import com.james.common.pojo.Collect;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ACT
 */
@Mapper
public interface CollectDao {

    /**
     * 添加收藏信息
     * @param collect 信息
     */
    void add(Collect collect);

    /**
     * 根据题目id和学生id查询是否存在
     * @param collect 信息
     * @return 结果
     */
    Collect findCollectByAllId(Collect collect);

    /**
     * 查询所有收藏
     * @return 结果集
     * @param studentId 学生id
     */
    List<Collect> findAllCollect(Integer studentId);

    /**
     * 删除收藏题目
     * @param collect 信息
     */
    void removeByAllId(Collect collect);
}
