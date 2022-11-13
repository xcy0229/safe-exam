package com.james.dao;

import com.github.pagehelper.Page;
import com.james.common.pojo.JudgmentQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ACT
 */
@Mapper
public interface JudgmentQuestionDao {

    /**
     * 判断题新增
     * @param judgmentQuestion 判断题
     */
    void add(JudgmentQuestion judgmentQuestion);

    /**
     * 根据条件查询
     * @param queryString 查询条件
     * @return 结果集
     */
    Page<JudgmentQuestion> selectByCondition(String queryString);

    /**
     * 编辑判断题
     * @param judgmentQuestion 判断题
     */
    void edit(JudgmentQuestion judgmentQuestion);

    /**
     * 根据id获取判断题信息
     * @param id 判断题id
     * @return 结果集
     */
    JudgmentQuestion findJudgmentInfoById(Integer id);

    /**
     * 根据id删除判断题
     * @param id 判断题id
     */
    void deleteById(Integer id);

    /**
     * 查询所有题目
     * @return 结果集
     */
    List<JudgmentQuestion> findAll();

    /**
     * 根据id获取答案
     * @param judgmentId 判断题id
     * @return 答案
     */
    int getAnswerById(Integer judgmentId);

    /**
     * 获取判断题总数
     * @return 结果
     */
    Integer getCountById();

    /**
     * 获取min
     * @return min
     */
    Integer getMin();

    /**
     * 获取max
     * @return max
     */
    Integer getMax();
}
