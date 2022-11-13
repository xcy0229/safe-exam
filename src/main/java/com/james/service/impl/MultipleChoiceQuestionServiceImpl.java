package com.james.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.james.common.enity.PageResult;
import com.james.common.enity.QueryPageBean;
import com.james.common.pojo.MultipleChoiceQuestions;
import com.james.system.bean.QuestionUtil;
import com.james.dao.MultipleChoiceQuestionDao;
import com.james.service.MultipleChoiceQuestionService;
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
public class MultipleChoiceQuestionServiceImpl implements MultipleChoiceQuestionService {

    @Autowired
    private MultipleChoiceQuestionDao multipleChoiceQuestionDao;

    @Autowired
    private QuestionUtil questionUtil;

    @Override
    public void add(MultipleChoiceQuestions multipleChoiceQuestions) {
        multipleChoiceQuestions.setMultiple_answer(questionUtil.answerSort(multipleChoiceQuestions.getMultiple_answer()));
        multipleChoiceQuestionDao.add(multipleChoiceQuestions);
    }

    @Override
    public PageResult findPages(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage, pageSize);
        Page<MultipleChoiceQuestions> page = multipleChoiceQuestionDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void edit(MultipleChoiceQuestions multipleChoiceQuestions) {
        multipleChoiceQuestionDao.edit(multipleChoiceQuestions);
    }

    @Override
    public void delete(Integer id) {
        multipleChoiceQuestionDao.deleteById(id);
    }

    @Override
    public List<MultipleChoiceQuestions> findMultipleChoice(Integer id) {
        List<MultipleChoiceQuestions> list = new ArrayList<>();
        list.add(multipleChoiceQuestionDao.findMultipleChoiceInfoById(id));
        return list;
    }

    @Override
    public Map<String, Object> getMultiple(QueryPageBean queryPageBean, Integer studentId) {
        Map<String, Object> result = new HashMap<>();
        PageResult pageResult = this.findPages(queryPageBean);
        Long total = pageResult.getTotal();
        List<MultipleChoiceQuestions> rows = (List<MultipleChoiceQuestions>) pageResult.getRows();
        result.put("total", total);
        result.put("question", questionUtil.multipleUtil(rows, studentId));
        return result;
    }
}
