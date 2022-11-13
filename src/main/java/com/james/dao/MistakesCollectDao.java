package com.james.dao;

import com.james.common.pojo.MistakesCollection;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ACT
 */
@Mapper
public interface MistakesCollectDao {

    /**
     * 添加错题集
     * @param mistakesCollect 错题
     */
    void add(MistakesCollection mistakesCollect);

    /**
     * 根据题目id和学生id查询是否存在
     * @param mistakesCollect 信息
     * @return 结果
     */
    MistakesCollection findMistakesCollectByAllId(MistakesCollection mistakesCollect);

    /**
     * 查询所有错题
     * @return 结果集
     * @param studentId 学生id
     */
    List<MistakesCollection> findAllMistakesCollect(Integer studentId);

    /**
     * 修改错题信息
     * @param mistakesCollect 错题
     */
    void edit(MistakesCollection mistakesCollect);
}
