package com.james.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.james.dao.CompleteListDao;
import com.james.dao.StudentDao;
import com.james.common.enity.PageResult;
import com.james.common.enity.QueryPageBean;
import com.james.common.pojo.StudentExamSituation;
import com.james.service.CompleteListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ACT
 */
@Service
@Transactional
public class CompleteListServiceImpl implements CompleteListService {

    @Autowired
    private CompleteListDao completeListDao;

    @Autowired
    private StudentDao studentDao;

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage, pageSize);
        Page<StudentExamSituation> page = completeListDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public List<StudentExamSituation> getExamPaper(Integer id) {
        return completeListDao.getExamPaperByUserUid(id);
    }

    @Override
    public Integer add(StudentExamSituation studentExamSituation) {
        completeListDao.add(studentExamSituation);
        Integer id = studentExamSituation.getUser_exam_id();
        return id;
    }

    @Override
    public StudentExamSituation findExamSheetById(Integer id) {
        StudentExamSituation studentExamSituation = completeListDao.findExamSheetById(id);
        if (null != studentExamSituation) {
            studentDao.editSituationById(studentExamSituation.getUser_id());
        }
        return studentExamSituation;
    }
}
