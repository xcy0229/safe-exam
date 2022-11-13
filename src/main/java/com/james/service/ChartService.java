package com.james.service;

import java.util.List;
import java.util.Map;

/**
 * @author ACT
 */
public interface ChartService {

    /**
     * 根据月份查询答卷数量
     * @param months 月份
     * @return 结果集
     */
    List<Integer> findAnswerSheetCountByMonths(List<String> months);

    /**
     * 获取各项数值
     * @return 结果集
     */
    Map<String, Integer> getCount();

    /**
     * 获取各项题目数值
     * @return 结果集
     */
    Integer[] getBarCount();
}
