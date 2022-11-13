package com.james.service.impl;

import com.james.common.pojo.Collect;
import com.james.common.pojo.JudgmentQuestion;
import com.james.common.pojo.MultipleChoiceQuestions;
import com.james.common.pojo.SingleChoiceQuestion;
import com.james.dao.CollectDao;
import com.james.dao.JudgmentQuestionDao;
import com.james.dao.MultipleChoiceQuestionDao;
import com.james.dao.SingleChoiceDao;
import com.james.service.CollectService;
import com.james.system.bean.QuestionUtil;
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
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectDao collectDao;

    @Autowired
    private QuestionUtil questionUtil;

    @Autowired
    private SingleChoiceDao singleChoiceDao;

    @Autowired
    private MultipleChoiceQuestionDao multipleQuestionDao;

    @Autowired
    private JudgmentQuestionDao judgmentQuestionDao;

    @Override
    public boolean add(Collect collect)  {
        Collect isCollect = collectDao.findCollectByAllId(collect);
        if (null != isCollect) {
            return false;
        }
        collectDao.add(collect);
        return true;
    }

    @Override
    public Map<String, Object> findAllCollect(Integer studentId) {
        List<Collect> allCollect = collectDao.findAllCollect(studentId);
        if (allCollect.size() == 0) {
            return null;
        }
        int total = allCollect.size();
        Map<String, Object> result = new HashMap<>();
        List<SingleChoiceQuestion> singleList = new ArrayList<>();
        List<MultipleChoiceQuestions> multipleList = new ArrayList<>();
        List<JudgmentQuestion> judgmentList = new ArrayList<>();
        for (Collect collect : allCollect) {
            if (null != collect.getQuestion_type() && null != collect.getQuestion_id() && null != collect.getStudent_id()) {
                if (collect.getQuestion_type() == 1) {
                    SingleChoiceQuestion single = singleChoiceDao.findSingleChoiceInfoById(collect.getQuestion_id());
                    singleList.add(single);
                } else if (collect.getQuestion_type() == 2) {
                    MultipleChoiceQuestions multiple = multipleQuestionDao.findMultipleChoiceInfoById(collect.getQuestion_id());
                    multipleList.add(multiple);
                } else if (collect.getQuestion_type() == 3) {
                    JudgmentQuestion judgment = judgmentQuestionDao.findJudgmentInfoById(collect.getQuestion_id());
                    judgmentList.add(judgment);
                }
            } else {
                total--;
            }
        }
        List<Map<String, Object>> singleMaps = questionUtil.singleUtil(singleList, allCollect.get(0).getStudent_id());
        List<Map<String, Object>> multipleMaps = questionUtil.multipleUtil(multipleList, allCollect.get(0).getStudent_id());
        List<Map<String, Object>> judgmentMaps = questionUtil.judgmentUtil(judgmentList, allCollect.get(0).getStudent_id());
        List<Map<String, Object>> questionList = new ArrayList<>(singleMaps);
        questionList.addAll(multipleMaps);
        questionList.addAll(judgmentMaps);
        result.put("total", total);
        result.put("question", questionList);
        return result;
    }

    @Override
    public void remove(Collect collect) {
        collectDao.removeByAllId(collect);
    }
}
