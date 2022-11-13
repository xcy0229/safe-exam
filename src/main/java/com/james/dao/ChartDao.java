package com.james.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author ACT
 */
@Mapper
public interface ChartDao {

    /**
     * 根据日期统计答卷数，统计指定日期之前的答卷数
     * @param date 日期
     * @return 结果集
     */
    Integer findAnswerSheetCountBeforeDate(String date);
}
