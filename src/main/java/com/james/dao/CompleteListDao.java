package com.james.dao;

import com.github.pagehelper.Page;
import com.james.common.pojo.StudentExamSituation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ACT
 */
@Mapper
public interface CompleteListDao {

    /**
     * 根据条件查询
     * @param queryString 查询条件
     * @return 结果集
     */
    Page<StudentExamSituation> selectByCondition(String queryString);

    /**
     * 获取答卷总数
     * @return 结果
     */
    Integer getCountById();

    /**
     * 根据用户id获取答卷
     * @param id 用户id
     * @return 结果集
     */
    List<StudentExamSituation> getExamPaperByUserUid(Integer id);

    /**
     * 添加答卷
     * @param studentExamSituation 答卷信息
     */
    void add(StudentExamSituation studentExamSituation);

    /**
     * 根据答卷id获取答卷
     * @param id 答卷id
     * @return 答卷
     */
    StudentExamSituation findExamSheetById(Integer id);
}
