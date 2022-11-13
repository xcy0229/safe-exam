package com.james.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.james.common.enity.PageResult;
import com.james.common.enity.QueryPageBean;
import com.james.common.pojo.JudgmentQuestion;
import com.james.system.bean.QuestionUtil;
import com.james.dao.JudgmentQuestionDao;
import com.james.service.JudgmentQuestionService;
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
public class JudgmentQuestionServiceImpl implements JudgmentQuestionService {

    @Autowired
    private JudgmentQuestionDao judgmentQuestionDao;

    @Autowired
    private QuestionUtil questionUtil;

    @Override
    public void add(JudgmentQuestion judgmentQuestion) {
        judgmentQuestionDao.add(judgmentQuestion);
    }

    @Override
    public PageResult findPages(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage, pageSize);
        Page<JudgmentQuestion> page = judgmentQuestionDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void edit(JudgmentQuestion judgmentQuestion) {
        judgmentQuestionDao.edit(judgmentQuestion);
    }

    @Override
    public List<JudgmentQuestion> findJudgmentInfo(Integer id) {
        List<JudgmentQuestion> list = new ArrayList<>();
        list.add(judgmentQuestionDao.findJudgmentInfoById(id));
        return list;
    }

    @Override
    public void delete(Integer id) {
        judgmentQuestionDao.deleteById(id);
    }

    @Override
    public Map<String, Object> getJudgment(QueryPageBean queryPageBean, Integer studentId) {
        Map<String, Object> result = new HashMap<>();
        PageResult pageResult = this.findPages(queryPageBean);
        Long total = pageResult.getTotal();
        List<JudgmentQuestion> rows = (List<JudgmentQuestion>) pageResult.getRows();
        result.put("total", total);
        result.put("question", questionUtil.judgmentUtil(rows, studentId));
        return result;
    }
}
