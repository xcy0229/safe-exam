package com.james.service;

import com.james.common.enity.PageResult;
import com.james.common.enity.QueryPageBean;
import com.james.common.pojo.MultipleChoiceQuestions;

import java.util.List;
import java.util.Map;

/**
 * @author ACT
 */
public interface MultipleChoiceQuestionService {

    /**
     * 添加多选题
     * @param multipleChoiceQuestions 多选题信息
     */
    void add(MultipleChoiceQuestions multipleChoiceQuestions);

    /**
     * 分页查询
     * @param queryPageBean 分页条件
     * @return 结果集
     */
    PageResult findPages(QueryPageBean queryPageBean);

    /**
     * 修改多选题
     * @param multipleChoiceQuestions 多选题信息
     */
    void edit(MultipleChoiceQuestions multipleChoiceQuestions);

    /**
     * 删除多选题
     * @param id 多选题id
     */
    void delete(Integer id);

    /**
     * 获取多选题
     * @param id 多选题id
     * @return 结果集
     */
    List<MultipleChoiceQuestions> findMultipleChoice(Integer id);

    /**
     * 移动端-多选专项训练-获取题目
     * @param queryPageBean 分页
     * @param studentId 学生id
     * @return 结果集
     */
    Map<String, Object> getMultiple(QueryPageBean queryPageBean, Integer studentId);
}
