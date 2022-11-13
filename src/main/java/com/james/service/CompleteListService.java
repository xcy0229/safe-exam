package com.james.service;

import com.james.common.enity.PageResult;
import com.james.common.enity.QueryPageBean;
import com.james.common.pojo.StudentExamSituation;

import java.util.List;

/**
 * @author ACT
 */
public interface CompleteListService {

    /**
     * 分页查询
     * @param queryPageBean 分页条件
     * @return 结果集
     */
    PageResult pageQuery(QueryPageBean queryPageBean);

    /**
     * 根据用户id获取答卷
     * @param id id
     * @return 结果集
     */
    List<StudentExamSituation> getExamPaper(Integer id);

    /**
     * 创建答卷
     * @param studentExamSituation 数据信息
     * @return 答卷id
     */
    Integer add(StudentExamSituation studentExamSituation);

    /**
     * 根据答卷id获取答卷
     * @param id id
     * @return 答卷
     */
    StudentExamSituation findExamSheetById(Integer id);
}
