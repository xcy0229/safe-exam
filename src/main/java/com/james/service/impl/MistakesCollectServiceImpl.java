package com.james.service.impl;

import com.james.common.domain.MisQuestion;
import com.james.common.pojo.*;
import com.james.dao.JudgmentQuestionDao;
import com.james.dao.MistakesCollectDao;
import com.james.dao.MultipleChoiceQuestionDao;
import com.james.dao.SingleChoiceDao;
import com.james.service.MistakesCollectService;
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
public class MistakesCollectServiceImpl implements MistakesCollectService {

    @Autowired
    private MistakesCollectDao mistakesCollectDao;

    @Autowired
    private SingleChoiceDao singleChoiceDao;

    @Autowired
    private MultipleChoiceQuestionDao multipleQuestionDao;

    @Autowired
    private JudgmentQuestionDao judgmentQuestionDao;

    @Autowired
    private QuestionUtil questionUtil;

    @Override
    public void add(MisQuestion misQuestion) {
        List<MistakesCollection> misSingle = misQuestion.getMisSingle();
        List<MistakesCollection> misJudgment = misQuestion.getMisJudgment();
        List<MistakesCollection> misMultiple = misQuestion.getMisMultiple();
        this.addQuestion(misSingle);
        this.addQuestion(misJudgment);
        this.addQuestion(misMultiple);
    }

    @Override
    public Map<String, Object> findAllCollect(Integer studentId) {
        List<MistakesCollection> allMistakesCollect = mistakesCollectDao.findAllMistakesCollect(studentId);
        if (allMistakesCollect.size() == 0) {
            return null;
        }
        int total = allMistakesCollect.size();
        Map<String, Object> result = new HashMap<>();
        List<SingleChoiceQuestion> singleList = new ArrayList<>();
        List<MultipleChoiceQuestions> multipleList = new ArrayList<>();
        List<JudgmentQuestion> judgmentList = new ArrayList<>();
        for (MistakesCollection mistakesCollection : allMistakesCollect) {
            if (null != mistakesCollection.getQuestion_type() && null != mistakesCollection.getQuestion_id() && null != mistakesCollection.getStudent_id()) {
                if (mistakesCollection.getQuestion_type() == 1) {
                    SingleChoiceQuestion single = singleChoiceDao.findSingleChoiceInfoById(mistakesCollection.getQuestion_id());
                    singleList.add(single);
                } else if (mistakesCollection.getQuestion_type() == 2) {
                    MultipleChoiceQuestions multiple = multipleQuestionDao.findMultipleChoiceInfoById(mistakesCollection.getQuestion_id());
                    multipleList.add(multiple);
                } else if (mistakesCollection.getQuestion_type() == 3) {
                    JudgmentQuestion judgment = judgmentQuestionDao.findJudgmentInfoById(mistakesCollection.getQuestion_id());
                    judgmentList.add(judgment);
                }
            } else {
                total--;
            }
        }
        List<Map<String, Object>> singleMaps = questionUtil.singleUtil(singleList, allMistakesCollect.get(0).getStudent_id());
        List<Map<String, Object>> multipleMaps = questionUtil.multipleUtil(multipleList, allMistakesCollect.get(0).getStudent_id());
        List<Map<String, Object>> judgmentMaps = questionUtil.judgmentUtil(judgmentList, allMistakesCollect.get(0).getStudent_id());
        List<Map<String, Object>> questionList = new ArrayList<>(singleMaps);
        questionList.addAll(multipleMaps);
        questionList.addAll(judgmentMaps);
        result.put("total", total);
        result.put("question", questionList);
        return result;
    }

    private void addQuestion(List<MistakesCollection> mistakesCollectionList) {
        for (MistakesCollection mistakesCollect : mistakesCollectionList) {
            MistakesCollection isNull = mistakesCollectDao.findMistakesCollectByAllId(mistakesCollect);
            if (null == isNull) {
                mistakesCollectDao.add(mistakesCollect);
            } else {
                mistakesCollect.setId(isNull.getId());
                mistakesCollectDao.edit(mistakesCollect);
            }
        }
    }

}
