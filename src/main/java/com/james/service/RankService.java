package com.james.service;

import com.james.common.pojo.StudentExamSituation;

import java.util.List;

/**
 * @author ACT
 */
public interface RankService {

    /**
     * 获取成绩排行信息
     * @return 结果集
     */
    List<StudentExamSituation> getRank();
}
