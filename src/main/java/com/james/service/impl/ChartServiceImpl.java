package com.james.service.impl;

import com.james.dao.*;
import com.james.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ACT
 */
@Service
@Transactional
public class ChartServiceImpl implements ChartService {

    @Autowired
    private ChartDao chartDao;

    @Autowired
    private ExamPaperDao examPaperDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private CompleteListDao completeListDao;

    @Autowired
    private SingleChoiceDao singleChoiceDao;
    @Autowired
    private MultipleChoiceQuestionDao multipleChoiceQuestionDao;
    @Autowired
    private JudgmentQuestionDao judgmentQuestionDao;

    @Override
    public List<Integer> findAnswerSheetCountByMonths(List<String> months) {
        List<Integer> answerSheetCount = new ArrayList<>();
        for (String month : months) {
            String date = month + ".31";
            Integer count = chartDao.findAnswerSheetCountBeforeDate(date);
            answerSheetCount.add(count);
        }
        return answerSheetCount;
    }

    @Override
    public Map<String, Integer> getCount() {
        Map<String, Integer> countMap = new HashMap<>();
        //获取题目总数
        Integer singleCount = singleChoiceDao.getCountById();
        Integer judgmentCount = judgmentQuestionDao.getCountById();
        Integer multipleCount = multipleChoiceQuestionDao.getCountById();
        Integer questionCount = singleCount + judgmentCount + multipleCount;
        countMap.put("questionCount", questionCount);
        //获取试卷总数
        Integer examPaperCount = examPaperDao.getCountById();
        countMap.put("examPaperCount", examPaperCount);
        //获取学生总数
        Integer studentCount = studentDao.getCountById();
        countMap.put("studentCount", studentCount);
        //获取答卷总数
        Integer answerSheetCount = completeListDao.getCountById();
        countMap.put("answerSheetCount", answerSheetCount);
        return countMap;
    }

    @Override
    public Integer[] getBarCount() {
        Integer singleCount = singleChoiceDao.getCountById();
        Integer judgmentCount = judgmentQuestionDao.getCountById();
        Integer multipleCount = multipleChoiceQuestionDao.getCountById();
        return new Integer[]{singleCount, multipleCount, judgmentCount};
    }
}
