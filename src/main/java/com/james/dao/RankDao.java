package com.james.dao;

import com.james.common.pojo.StudentExamSituation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ACT
 */
@Mapper
public interface RankDao {

    /**
     * 获取成绩排行信息
     * @return 结果集
     */
    List<StudentExamSituation> getRank();
}
