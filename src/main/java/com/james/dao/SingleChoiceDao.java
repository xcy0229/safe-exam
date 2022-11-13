package com.james.dao;

import com.github.pagehelper.Page;
import com.james.common.pojo.SingleChoiceQuestion;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ACT
 */
@Mapper
public interface SingleChoiceDao {

    /**
     * 添加单选题
     * @param singleChoiceQuestion 单选题信息
     */
    void add(SingleChoiceQuestion singleChoiceQuestion);

    /**
     * 根据条件查询
     * @param queryString 条件
     * @return 结果集
     */
    Page<SingleChoiceQuestion> selectByCondition(String queryString);

    /**
     * 编辑单选题
     * @param singleChoiceQuestion 单选题信息
     */
    void edit(SingleChoiceQuestion singleChoiceQuestion);

    /**
     * 根据选择题id查询选择题信息
     * @param id 选择题id
     * @return 选择题信息
     */
    SingleChoiceQuestion findSingleChoiceInfoById(Integer id);

    /**
     * 根据id删除单选题
     * @param id 单选题id
     */
    void deleteById(Integer id);

    /**
     * 查询所有题目
     * @return 结果集
     */
    List<SingleChoiceQuestion> findAll();

    /**
     * 根据id获取答案
     * @param singleId 单选题id
     * @return 答案
     */
    String getAnswerById(Integer singleId);

    /**
     * 获取单选题总数
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
