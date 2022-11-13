package com.james.dao;

import com.github.pagehelper.Page;
import com.james.common.pojo.MultipleChoiceQuestions;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ACT
 */
@Mapper
public interface MultipleChoiceQuestionDao {

    /**
     * 创建多选题
     * @param multipleChoiceQuestions 多选题
     */
    void add(MultipleChoiceQuestions multipleChoiceQuestions);

    /**
     * 根据条件查询
     * @param queryString 查询条件
     * @return 结果集
     */
    Page<MultipleChoiceQuestions> selectByCondition(String queryString);

    /**
     * 修改多选题信息
     * @param multipleChoiceQuestions 多选题
     */
    void edit(MultipleChoiceQuestions multipleChoiceQuestions);

    /**
     * 根据id删除多选题
     * @param id 多选题id
     */
    void deleteById(Integer id);

    /**
     * 根据id查询多选题信息
     * @param id 多选题id
     * @return 多选题信息
     */
    MultipleChoiceQuestions findMultipleChoiceInfoById(Integer id);

    /**
     * 查询所有题目
     * @return 结果集
     */
    List<MultipleChoiceQuestions> findAll();

    /**
     * 根据id获取答案
     * @param multipleId 多选题id
     * @return 答案
     */
    String getAnswerById(Integer multipleId);

    /**
     * 获取多选题总数
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
