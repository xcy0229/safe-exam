package com.james.dao;

import com.github.pagehelper.Page;
import com.james.common.pojo.ExamPaper;
import com.james.common.pojo.ExamQuestion;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ACT
 */
@Mapper
public interface ExamPaperDao {

    /**
     * 根据条件查询
     * @param queryString 查询条件
     * @return 结果集
     */
    Page<ExamPaper> selectByCondition(String queryString);

    /**
     * 根据id删除
     * @param id 试卷id
     */
    void deleteById(Integer id);

    /**
     * 添加试卷
     * @param examPaper 试卷
     */
    void add(ExamPaper examPaper);

    /**
     * 设置试卷与单选题的关联
     * @param question 单选题
     */
    void setExamPaperAndSingle(ExamQuestion question);

    /**
     * 设置试卷与多选题的关联
     * @param question 多选题
     */
    void setExamPaperAndMultiple(ExamQuestion question);

    /**
     * 设置试卷与判断题的关联
     * @param question 判断题
     */
    void setExamPaperAndJudgment(ExamQuestion question);

    /**
     * 根据试卷id删除问题
     * @param id 试卷id
     */
    void delQuestionByPaperId(Integer id);

    /**
     * 根据试卷id获取问题
     * @param id 试卷id
     * @return 结果集
     */
    List<ExamQuestion> findQuestionByPaperId(Integer id);

    /**
     * 修改试卷基本信息
     * @param examPaper 试卷信息
     */
    void edit(ExamPaper examPaper);

    /**
     * 获取试卷总数
     * @return 结果
     */
    Integer getCountById();

    /**
     * 获取max
     * @return max
     */
    Integer getMax();

    /**
     * 获取min
     * @return min
     */
    Integer getMin();

    /**
     * 获取所有考试卷id
     * @return 结果id
     */
    List<Integer> getExamPaperId();

    /**
     * 根据id查询试卷
     * @param randomId 试卷id
     * @return 试卷
     */
    ExamPaper getExamPaperById(Integer randomId);

    /**
     * 根据试卷id和试题类型获取试题
     * @param randomId 试卷id
     * @param i 试题类型
     * @return 结果集
     */
    List<ExamQuestion> findQuestionByPaperIdAndType(Integer randomId, int i);

    /**
     * 是否将试卷设为考试卷
     * @param id id
     * @param status status
     */
    void editDefaultPaper(Integer id, Integer status);

    /**
     * 获取测试卷id
     * @return 结果id
     */
    List<Integer> getTestPaperId();
}
