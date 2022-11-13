package com.james.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.james.common.enity.PageResult;
import com.james.common.enity.QueryPageBean;
import com.james.common.pojo.SingleChoiceQuestion;
import com.james.system.bean.QuestionUtil;
import com.james.dao.SingleChoiceDao;
import com.james.service.SingleChoiceService;
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
public class SingleChoiceServiceImpl implements SingleChoiceService {

    @Autowired
    private SingleChoiceDao singleChoiceDao;

    @Autowired
    private QuestionUtil questionUtil;

    @Override
    public void add(SingleChoiceQuestion singleChoiceQuestion) {
        singleChoiceDao.add(singleChoiceQuestion);
    }

    @Override
    public PageResult findPages(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage, pageSize);
        Page<SingleChoiceQuestion> page = singleChoiceDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void edit(SingleChoiceQuestion singleChoiceQuestion) {
        singleChoiceDao.edit(singleChoiceQuestion);
    }

    @Override
    public List<SingleChoiceQuestion> findSingleChoiceInfo(Integer id) {
        List<SingleChoiceQuestion> list = new ArrayList<>();
        list.add(singleChoiceDao.findSingleChoiceInfoById(id));
        return list;
    }

    @Override
    public void delete(Integer id) {
        singleChoiceDao.deleteById(id);
    }

    @Override
    public Map<String, Object> getSingle(QueryPageBean queryPageBean, Integer studentId) {
        Map<String, Object> result = new HashMap<>();
        PageResult pageResult = this.findPages(queryPageBean);
        Long total = pageResult.getTotal();
        List<SingleChoiceQuestion> rows = (List<SingleChoiceQuestion>) pageResult.getRows();
        result.put("total", total);
        result.put("question", questionUtil.singleUtil(rows, studentId));
        return result;
    }
}
