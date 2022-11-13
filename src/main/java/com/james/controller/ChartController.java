package com.james.controller;

import com.james.common.constant.MessageConstant;
import com.james.common.enity.Result;
import com.james.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ACT
 * 首页图表
 */
@RestController
@RequestMapping("/chart")
public class ChartController {

    @Autowired
    private ChartService chartService;

    @RequestMapping("/getChart")
    public Result getChart() {
        Map<String, Object> map = new HashMap<>();
        List<String> months = new ArrayList<>();
        //获得日历对象，模拟时间就是当前时间
        Calendar calendar = Calendar.getInstance();
        //计算过去一年的12个月
        //获得当前时间往前推12个月的时间
        calendar.add(Calendar.MONTH, -12);
        for (int i = 0; i < MessageConstant.MOUTH_SUM; i++) {
            //获得当前时间往后推一个月日期
            calendar.add(Calendar.MONTH, 1);
            Date date = calendar.getTime();
            months.add(new SimpleDateFormat("yyyy.MM").format(date));
        }
        map.put("months", months);

        List<Integer> answerSheet = chartService.findAnswerSheetCountByMonths(months);
        map.put("answerSheet", answerSheet);

        return new Result(true, MessageConstant.GET_ANSWER_SHEET_COUNT_TRUE, map);
    }

    @RequestMapping("/findCount")
    public Result findCount() {
        Map<String, Integer> countMap;
        try {
            countMap = chartService.getCount();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_COUNT_FALSE);
        }
        return new Result(true, MessageConstant.GET_COUNT_TRUE, countMap);
    }

    @RequestMapping("/getBarChart")
    public Result getBarChart() {
        Map<String, Object> result = new HashMap<>();
        String[] str = new String[] {"单选题", "多选题", "判断题"};
        result.put("questionType", str);
        try {
            Integer[] count = chartService.getBarCount();
            result.put("questionCount", count);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_QUESTION_COUNT_FALSE);
        }
        return new Result(true, MessageConstant.GET_QUESTION_COUNT_TRUE, result);
    }
}
