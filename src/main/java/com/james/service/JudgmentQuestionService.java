package com.james.service;

import com.james.common.enity.PageResult;
import com.james.common.enity.QueryPageBean;
import com.james.common.pojo.JudgmentQuestion;

import java.util.List;
import java.util.Map;

/**
 * @author ACT
 */
public interface JudgmentQuestionService {

    /**
     * 判断题新建
     * @param judgmentQuestion 判断题
     */
    void add(JudgmentQuestion judgmentQuestion);

    /**
     * 分页查询
     * @param queryPageBean 分页条件
     * @return 结果集
     */
    PageResult findPages(QueryPageBean queryPageBean);

    /**
     * 判断题编辑
     * @param judgmentQuestion 判断题信息
     */
    void edit(JudgmentQuestion judgmentQuestion);

    /**
     * 根据id获取判断题信息
     * @param id 判断题id
     * @return 结果集
     */
    List<JudgmentQuestion> findJudgmentInfo(Integer id);

    /**
     * 删除判断题
     * @param id 判断题id
     */
    void delete(Integer id);

    /**
     * 移动端-判断专项训练-获取题目
     * @param queryPageBean 分页
     * @param studentId 学生id
     * @return 结果集
     */
    Map<String, Object> getJudgment(QueryPageBean queryPageBean, Integer studentId);
}
