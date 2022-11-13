package com.james.service;

import com.james.common.enity.PageResult;
import com.james.common.enity.QueryPageBean;
import com.james.common.pojo.ExamPaper;

import java.util.List;
import java.util.Map;

/**
 * @author ACT
 */
public interface ExamPaperService {

    /**
     * 查询题库
     * @return 结果集
     */
    List<Object> findAll();

    /**
     * 分页查询
     * @param queryPageBean 分页条件
     * @return 结果集
     */
    PageResult findPage(QueryPageBean queryPageBean);

    /**
     * 删除试卷
     * @param id 试卷id
     */
    void delete(Integer id);

    /**
     * 添加试卷
     * @param singleIds 单选题
     * @param multipleIds 多选题
     * @param judgmentIds 判断题
     * @param examPaper 试卷信息
     */
    void add(Integer[] singleIds, Integer[] multipleIds, Integer[] judgmentIds, ExamPaper examPaper);

    /**
     * 根据试卷id获取问题ids
     * @param id 试卷id
     * @return 结果集
     */
    List<List<Integer>> findQuestionIdsByPaperId(Integer id);

    /**
     * 修改试卷
     * @param singleIds 单选题
     * @param multipleIds 多选题
     * @param judgmentIds 判断题
     * @param examPaper 试卷信息
     */
    void edit(Integer[] singleIds, Integer[] multipleIds, Integer[] judgmentIds, ExamPaper examPaper);

    /**
     * 随机创建试卷
     * @param examPaper 试卷信息
     */
    void randomAdd(ExamPaper examPaper);

    /**
     * 获取试卷
     * @return 试卷
     */
    Map<String, Object> getExamPaper();

    /**
     * 是否将试卷设为考试卷
     * @param id id
     * @param status status
     */
    void editDefault(Integer id, Integer status);

    /**
     * 获取测试卷
     * @return 试卷
     */
    Map<String, Object> getTestPaper();
}
