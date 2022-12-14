package com.james.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.james.common.enity.PageResult;
import com.james.common.enity.QueryPageBean;
import com.james.common.pojo.*;
import com.james.dao.ExamPaperDao;
import com.james.dao.JudgmentQuestionDao;
import com.james.dao.MultipleChoiceQuestionDao;
import com.james.dao.SingleChoiceDao;
import com.james.service.ExamPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author ACT
 */
@Service
@Transactional
public class ExamPaperServiceImpl implements ExamPaperService {

    @Autowired
    private ExamPaperDao examPaperDao;

    @Autowired
    private SingleChoiceDao singleChoiceDao;

    @Autowired
    private JudgmentQuestionDao judgmentQuestionDao;

    @Autowired
    private MultipleChoiceQuestionDao multipleChoiceQuestionDao;

    @Override
    public List<Object> findAll() {
        List<SingleChoiceQuestion> singleList = singleChoiceDao.findAll();
        List<JudgmentQuestion> judgmentList = judgmentQuestionDao.findAll();
        List<MultipleChoiceQuestions> multipleList = multipleChoiceQuestionDao.findAll();
        List<Object> questionList = new ArrayList<>();
        questionList.add(singleList);
        questionList.add(judgmentList);
        questionList.add(multipleList);
        return questionList;
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage, pageSize);
        Page<ExamPaper> page = examPaperDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void delete(Integer id) {
        examPaperDao.deleteById(id);
        examPaperDao.delQuestionByPaperId(id);
    }

    @Override
    public void add(Integer[] singleIds, Integer[] multipleIds, Integer[] judgmentIds, ExamPaper examPaper) {
        //??????????????????
        Integer count = singleIds.length + multipleIds.length + judgmentIds.length;
        examPaper.setQuestion_count(count);
        //?????????????????????
        examPaper.setDefault_paper(2);
        examPaperDao.add(examPaper);
        //????????????id
        Integer examPaperId = examPaper.getId();
        //????????????
        Integer questionScore = examPaper.getScore() / examPaper.getQuestion_count();
        try {
            //?????????????????????????????????
            setExamPaperAndSingle(examPaperId,questionScore , singleIds);
            //?????????????????????????????????
            setExamPaperAndMultiple(examPaperId, questionScore, multipleIds);
            //?????????????????????????????????
            setExamPaperAndJudgment(examPaperId, questionScore, judgmentIds);
        } catch (Exception e) {
            e.printStackTrace();
            this.delete(examPaperId);
        }
    }

    @Override
    public List<List<Integer>> findQuestionIdsByPaperId(Integer id) {
        List<List<Integer>> ids = new ArrayList<>();
        List<Integer> singleIds = new ArrayList<>();
        List<Integer> judgmentIds = new ArrayList<>();
        List<Integer> multipleIds = new ArrayList<>();
        List<ExamQuestion> questionList = examPaperDao.findQuestionByPaperId(id);
        for (ExamQuestion question : questionList) {
            Integer type = question.getExam_question_type();
            if (type == 1) {
                singleIds.add(question.getQuestion_id());
            } else if (type == 2) {
                multipleIds.add(question.getQuestion_id());
            } else if (type == 3) {
                judgmentIds.add(question.getQuestion_id());
            }
        }
        ids.add(singleIds);
        ids.add(multipleIds);
        ids.add(judgmentIds);
        return ids;
    }

    @Override
    public void edit(Integer[] singleIds, Integer[] multipleIds, Integer[] judgmentIds, ExamPaper examPaper) {
        //????????????id????????????
        examPaperDao.delQuestionByPaperId(examPaper.getId());
        //????????????????????????
        examPaperDao.edit(examPaper);
        //????????????
        Integer questionScore = examPaper.getScore() / examPaper.getQuestion_count();
        //?????????????????????????????????
        setExamPaperAndSingle(examPaper.getId(),questionScore , singleIds);
        //?????????????????????????????????
        setExamPaperAndMultiple(examPaper.getId(), questionScore, multipleIds);
        //?????????????????????????????????
        setExamPaperAndJudgment(examPaper.getId(), questionScore, judgmentIds);
    }

    @Override
    public void randomAdd(ExamPaper examPaper) {
        //??????????????????
        if (null == examPaper.getName() || "".equals(examPaper.getName())) {
            examPaper.setName("??????????????????????????????");
        }
        examPaper.setQuestion_count(50);
        examPaper.setScore(100);
        examPaper.setDefault_paper(2);
        examPaperDao.add(examPaper);
        Integer examPaperId = examPaper.getId();
        //???????????????
        setRandomExamPaperAndSingle(examPaperId);
        //???????????????
        setRandomExamPaperAndMultiple(examPaperId);
        //???????????????
        setRandomExamPaperAndJudgment(examPaperId);
    }

    @Override
    public Map<String, Object> getExamPaper() {
        // ???????????????id
        List<Integer> examPaperId = examPaperDao.getExamPaperId();
        if (null == examPaperId || examPaperId.size() < 1) {
            examPaperId = new ArrayList<>();
            examPaperId.add(1);
        }
        Map<String, Object> result = this.getPaper(this.getRandomId(examPaperId));
        return result;
    }

    @Override
    public Map<String, Object> getTestPaper() {
        // ???????????????id
        List<Integer> examPaperId = examPaperDao.getTestPaperId();
        if (null == examPaperId || examPaperId.size() < 1) {
            examPaperId = new ArrayList<>();
            examPaperId.add(1);
        }
        Map<String, Object> result = this.getPaper(this.getRandomId(examPaperId));
        return result;
    }

    @Override
    public void editDefault(Integer id, Integer status) {
        examPaperDao.editDefaultPaper(id, status);
    }

    /**
     * ??????????????????????????????
     */
    private void setRandomExamPaperAndJudgment(Integer examPaperId) {
        ExamQuestion question = new ExamQuestion();
        question.setExam_paper_id(examPaperId);
        question.setExam_question_type(3);
        question.setQuestion_score(2);
        //???????????????
        Integer min = judgmentQuestionDao.getMin();
        //???????????????
        Integer max = judgmentQuestionDao.getMax();
        List<Integer> judgmentIds = getIdsByRandom(15, max, min);
        //?????????????????????????????????
        if (judgmentIds.size() > 0) {
            for (Integer judgmentId : judgmentIds) {
                question.setQuestion_id(judgmentId);
                int answer = judgmentQuestionDao.getAnswerById(judgmentId);
                question.setAnswer(String.valueOf(answer));
                examPaperDao.setExamPaperAndJudgment(question);
            }
        }
    }

    /**
     * ??????????????????????????????
     */
    private void setRandomExamPaperAndMultiple(Integer examPaperId) {
        ExamQuestion question = new ExamQuestion();
        question.setExam_paper_id(examPaperId);
        question.setExam_question_type(2);
        question.setQuestion_score(2);
        //???????????????
        Integer min = multipleChoiceQuestionDao.getMin();
        //???????????????
        Integer max = multipleChoiceQuestionDao.getMax();
        List<Integer> multipleIds = getIdsByRandom(15, max, min);
        //?????????????????????????????????
        if (multipleIds.size() > 0) {
            for (Integer multipleId : multipleIds) {
                question.setQuestion_id(multipleId);
                String answer = multipleChoiceQuestionDao.getAnswerById(multipleId);
                question.setAnswer(answer);
                examPaperDao.setExamPaperAndMultiple(question);
            }
        }
    }

    /**
     * ??????????????????????????????
     */
    private void setRandomExamPaperAndSingle(Integer examPaperId) {
        //????????????
        ExamQuestion question = new ExamQuestion();
        question.setExam_paper_id(examPaperId);
        question.setExam_question_type(1);
        question.setQuestion_score(2);
        //???????????????
        Integer min = singleChoiceDao.getMin();
        //???????????????
        Integer max = singleChoiceDao.getMax();
        List<Integer> singleIds = getIdsByRandom(20, max, min);
        //?????????????????????????????????
        if (singleIds.size() > 0) {
            for (Integer singleId : singleIds) {
                question.setQuestion_id(singleId);
                String answer = singleChoiceDao.getAnswerById(singleId);
                question.setAnswer(answer);
                examPaperDao.setExamPaperAndSingle(question);
            }
        }
    }

    /**
     * ???????????????????????????????????????????????????
     * @param num ??????
     * @param max ?????????
     * @param min ?????????
     * @return ??????
     */
    private List<Integer> getIdsByRandom(Integer num, Integer max, Integer min) {
        Random random = new Random();
        List<Integer> idList = new ArrayList<>();
        int randomId;
        for (int i = 0; i < num; i++) {
            randomId = min + random.nextInt(max - min + 1);
            idList.add(randomId);
        }
        return idList;
    }

    /**
     * ?????????????????????????????????
     * @param examPaperId ??????id
     * @param questionScore ????????????
     * @param judgmentIds ?????????ids
     */
    private void setExamPaperAndJudgment(Integer examPaperId, Integer questionScore, Integer[] judgmentIds) {
        ExamQuestion question = new ExamQuestion();
        question.setExam_paper_id(examPaperId);
        question.setExam_question_type(3);
        question.setQuestion_score(questionScore);
        //?????????????????????????????????
        if (judgmentIds != null && judgmentIds.length > 0) {
            for (Integer judgmentId : judgmentIds) {
                question.setQuestion_id(judgmentId);
                int answer = judgmentQuestionDao.getAnswerById(judgmentId);
                question.setAnswer(String.valueOf(answer));
                examPaperDao.setExamPaperAndJudgment(question);
            }
        }
    }

    /**
     * ?????????????????????????????????
     * @param examPaperId ??????id
     * @param questionScore ????????????
     * @param multipleIds ?????????ids
     */
    private void setExamPaperAndMultiple(Integer examPaperId, Integer questionScore, Integer[] multipleIds) {
        ExamQuestion question = new ExamQuestion();
        question.setExam_paper_id(examPaperId);
        question.setExam_question_type(2);
        question.setQuestion_score(questionScore);
        //?????????????????????????????????
        if (multipleIds != null && multipleIds.length > 0) {
            for (Integer multipleId : multipleIds) {
                question.setQuestion_id(multipleId);
                String answer = multipleChoiceQuestionDao.getAnswerById(multipleId);
                question.setAnswer(answer);
                examPaperDao.setExamPaperAndMultiple(question);
            }
        }
    }

    /**
     * ?????????????????????????????????
     * @param examPaperId ??????id
     * @param questionScore ????????????
     * @param singleIds ?????????ids
     */
    private void setExamPaperAndSingle(Integer examPaperId, Integer questionScore, Integer[] singleIds) {
        ExamQuestion question = new ExamQuestion();
        question.setExam_paper_id(examPaperId);
        question.setExam_question_type(1);
        question.setQuestion_score(questionScore);
        //?????????????????????????????????
        if (singleIds != null && singleIds.length > 0) {
            for (Integer singleId : singleIds) {
                question.setQuestion_id(singleId);
                String answer = singleChoiceDao.getAnswerById(singleId);
                question.setAnswer(answer);
                examPaperDao.setExamPaperAndSingle(question);
            }
        }
    }

    /**
     * ??????????????????id
     * @param examPaperId ??????id??????
     * @return ??????id
     */
    private Integer getRandomId(List<Integer> examPaperId) {
        //??????id?????????
        int max = examPaperId.size();
        int randomId;
        if (max != 0) {
            //????????????id
            Random random = new Random();
            randomId = random.nextInt(max);
        } else {
            randomId = 0;
        }
        return examPaperId.get(randomId);
    }

    /**
     * ??????????????????
     * @param id ??????id
     * @return ??????
     */
    private Map<String, Object> getPaper(Integer id) {
        Map<String, Object> result = new HashMap<>();
        //??????id????????????
        ExamPaper examPaper = examPaperDao.getExamPaperById(id);
        result.put("examPaper", examPaper);
        //????????????id???????????????????????????
        List<ExamQuestion> singleList = examPaperDao.findQuestionByPaperIdAndType(examPaper.getId(), 1);
        List<ExamQuestion> multipleList = examPaperDao.findQuestionByPaperIdAndType(examPaper.getId(), 2);
        List<ExamQuestion> judgmentList = examPaperDao.findQuestionByPaperIdAndType(examPaper.getId(), 3);
        //??????????????????
        for (ExamQuestion question : singleList) {
            question.setSingleChoiceQuestion(singleChoiceDao.findSingleChoiceInfoById(question.getQuestion_id()));
        }
        for (ExamQuestion question : multipleList) {
            question.setMultipleChoiceQuestions(multipleChoiceQuestionDao.findMultipleChoiceInfoById(question.getQuestion_id()));
        }
        for (ExamQuestion question : judgmentList) {
            question.setJudgmentQuestion(judgmentQuestionDao.findJudgmentInfoById(question.getQuestion_id()));
        }
        result.put("singleList", singleList);
        result.put("multipleList", multipleList);
        result.put("judgmentList", judgmentList);
        return result;
    }
}
