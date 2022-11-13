package com.james.service;

import com.james.common.enity.PageResult;
import com.james.common.enity.QueryPageBean;
import com.james.common.pojo.SingleChoiceQuestion;

import java.util.List;
import java.util.Map;

/**
 * @author ACT
 */
public interface SingleChoiceService {

    /**
     * 添加单选题
     * @param singleChoiceQuestion 单选题信息
     */
    void add(SingleChoiceQuestion singleChoiceQuestion);

    /**
     * 分页展示
     * @param queryPageBean 分页条件
     * @return 结果集
     */
    PageResult findPages(QueryPageBean queryPageBean);

    /**
     * 编辑单选题
     * @param singleChoiceQuestion 单选题信息
     */
    void edit(SingleChoiceQuestion singleChoiceQuestion);

    /**
     * 根据id查询单选题信息
     * @param id 单选题id
     * @return 结果集
     */
    List<SingleChoiceQuestion> findSingleChoiceInfo(Integer id);

    /**
     * 删除单选题
     * @param id 单选题id
     */
    void delete(Integer id);

    /**
     * 移动端-单选专项训练-获取题目
     * @param queryPageBean 分页
     * @param studentId 学生id
     * @return 结果集
     */
    Map<String, Object> getSingle(QueryPageBean queryPageBean, Integer studentId);
}
