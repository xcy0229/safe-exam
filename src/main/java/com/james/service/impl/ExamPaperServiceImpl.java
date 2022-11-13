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
        //重置题目总数
        Integer count = singleIds.length + multipleIds.length + judgmentIds.length;
        examPaper.setQuestion_count(count);
        //将试卷信息添加
        examPaper.setDefault_paper(2);
        examPaperDao.add(examPaper);
        //获取试卷id
        Integer examPaperId = examPaper.getId();
        //题目分数
        Integer questionScore = examPaper.getScore() / examPaper.getQuestion_count();
        try {
            //添加试卷与单选题的关联
            setExamPaperAndSingle(examPaperId,questionScore , singleIds);
            //添加试卷与多选题的关联
            setExamPaperAndMultiple(examPaperId, questionScore, multipleIds);
            //添加试卷与判断题的关联
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
        //根据试卷id删除问题
        examPaperDao.delQuestionByPaperId(examPaper.getId());
        //修改试卷基本信息
        examPaperDao.edit(examPaper);
        //题目分数
        Integer questionScore = examPaper.getScore() / examPaper.getQuestion_count();
        //添加试卷与单选题的关联
        setExamPaperAndSingle(examPaper.getId(),questionScore , singleIds);
        //添加试卷与多选题的关联
        setExamPaperAndMultiple(examPaper.getId(), questionScore, multipleIds);
        //添加试卷与判断题的关联
        setExamPaperAndJudgment(examPaper.getId(), questionScore, judgmentIds);
    }

    @Override
    public void randomAdd(ExamPaper examPaper) {
        //添加试卷信息
        if (null == examPaper.getName() || "".equals(examPaper.getName())) {
            examPaper.setName("实验室安全教育考试卷");
        }
        examPaper.setQuestion_count(50);
        examPaper.setScore(100);
        examPaper.setDefault_paper(2);
        examPaperDao.add(examPaper);
        Integer examPaperId = examPaper.getId();
        //关联单选题
        setRandomExamPaperAndSingle(examPaperId);
        //关联多选题
        setRandomExamPaperAndMultiple(examPaperId);
        //关联判断题
        setRandomExamPaperAndJudgment(examPaperId);
    }

    @Override
    public Map<String, Object> getExamPaper() {
        // 获取考试卷id
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
        // 获取测试卷id
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
     * 将随机判断题关联试卷
     */
    private void setRandomExamPaperAndJudgment(Integer examPaperId) {
        ExamQuestion question = new ExamQuestion();
        question.setExam_paper_id(examPaperId);
        question.setExam_question_type(3);
        question.setQuestion_score(2);
        //获取最小值
        Integer min = judgmentQuestionDao.getMin();
        //获取最大值
        Integer max = judgmentQuestionDao.getMax();
        List<Integer> judgmentIds = getIdsByRandom(15, max, min);
        //设置试卷与多选题的关联
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
     * 将随机多选题关联试卷
     */
    private void setRandomExamPaperAndMultiple(Integer examPaperId) {
        ExamQuestion question = new ExamQuestion();
        question.setExam_paper_id(examPaperId);
        question.setExam_question_type(2);
        question.setQuestion_score(2);
        //获取最小值
        Integer min = multipleChoiceQuestionDao.getMin();
        //获取最大值
        Integer max = multipleChoiceQuestionDao.getMax();
        List<Integer> multipleIds = getIdsByRandom(15, max, min);
        //设置试卷与多选题的关联
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
     * 将随机单选题关联试卷
     */
    private void setRandomExamPaperAndSingle(Integer examPaperId) {
        //关联对象
        ExamQuestion question = new ExamQuestion();
        question.setExam_paper_id(examPaperId);
        question.setExam_question_type(1);
        question.setQuestion_score(2);
        //获取最小值
        Integer min = singleChoiceDao.getMin();
        //获取最大值
        Integer max = singleChoiceDao.getMax();
        List<Integer> singleIds = getIdsByRandom(20, max, min);
        //设置试卷与单选题的关联
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
     * 根据指定数量以及范围，随机生成数组
     * @param num 数值
     * @param max 最大值
     * @param min 最小值
     * @return 结果
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
     * 设置试卷与判断题的关联
     * @param examPaperId 试卷id
     * @param questionScore 试题分数
     * @param judgmentIds 判断题ids
     */
    private void setExamPaperAndJudgment(Integer examPaperId, Integer questionScore, Integer[] judgmentIds) {
        ExamQuestion question = new ExamQuestion();
        question.setExam_paper_id(examPaperId);
        question.setExam_question_type(3);
        question.setQuestion_score(questionScore);
        //设置试卷与多选题的关联
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
     * 设置试卷与多选题的关联
     * @param examPaperId 试卷id
     * @param questionScore 试题分数
     * @param multipleIds 多选题ids
     */
    private void setExamPaperAndMultiple(Integer examPaperId, Integer questionScore, Integer[] multipleIds) {
        ExamQuestion question = new ExamQuestion();
        question.setExam_paper_id(examPaperId);
        question.setExam_question_type(2);
        question.setQuestion_score(questionScore);
        //设置试卷与多选题的关联
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
     * 设置试卷与单选题的关联
     * @param examPaperId 试卷id
     * @param questionScore 试题分数
     * @param singleIds 单选题ids
     */
    private void setExamPaperAndSingle(Integer examPaperId, Integer questionScore, Integer[] singleIds) {
        ExamQuestion question = new ExamQuestion();
        question.setExam_paper_id(examPaperId);
        question.setExam_question_type(1);
        question.setQuestion_score(questionScore);
        //设置试卷与单选题的关联
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
     * 获取随机试卷id
     * @param examPaperId 试卷id集合
     * @return 试卷id
     */
    private Integer getRandomId(List<Integer> examPaperId) {
        //试卷id最大值
        int max = examPaperId.size();
        int randomId;
        if (max != 0) {
            //获取随机id
            Random random = new Random();
            randomId = random.nextInt(max);
        } else {
            randomId = 0;
        }
        return examPaperId.get(randomId);
    }

    /**
     * 随机获取试卷
     * @param id 试卷id
     * @return 试卷
     */
    private Map<String, Object> getPaper(Integer id) {
        Map<String, Object> result = new HashMap<>();
        //根据id获取试卷
        ExamPaper examPaper = examPaperDao.getExamPaperById(id);
        result.put("examPaper", examPaper);
        //根据试卷id和试题类型获取试题
        List<ExamQuestion> singleList = examPaperDao.findQuestionByPaperIdAndType(examPaper.getId(), 1);
        List<ExamQuestion> multipleList = examPaperDao.findQuestionByPaperIdAndType(examPaper.getId(), 2);
        List<ExamQuestion> judgmentList = examPaperDao.findQuestionByPaperIdAndType(examPaper.getId(), 3);
        //添加各项题目
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
