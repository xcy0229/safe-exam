package com.james.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.james.common.enity.QueryPageBean;
import com.james.common.pojo.SingleChoiceQuestion;
import com.james.dao.SingleChoiceDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @author ACT
 */
@SpringBootTest
public class TestSingleChoiceService {

    @Autowired
    private SingleChoiceService singleChoiceService;

    @Autowired
    private SingleChoiceDao singleChoiceDao;

    @Test
    void findPages() {
        int currentPage = 1;
        int pageSize = 10;
        String queryString = "";
        PageHelper.startPage(currentPage, pageSize);
        Page<SingleChoiceQuestion> page = singleChoiceDao.selectByCondition(queryString);
        List<SingleChoiceQuestion> result = page.getResult();
        for (SingleChoiceQuestion question : result) {
            System.out.println(question);
        }
    }

    @Test
    void getSingle() {
        QueryPageBean queryPageBean = new QueryPageBean(1, 10, null);
        Map<String, Object> single = singleChoiceService.getSingle(queryPageBean, null);
        Object question = single.get("question");
    }

    @Test
    void selectPage() {
        int currentPage = 1;
        int pageSize = 10;
        //QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        //wrapper.like("name", "a");
        //com.baomidou.mybatisplus.extension.plugins.pagination.Page<SingleChoiceQuestion> page =
        //        new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(currentPage, pageSize);
        //page = singleChoiceDao.selectPage(page, null);
        //List<SingleChoiceQuestion> result = page.getRecords();
        //for (SingleChoiceQuestion question : result) {
        //    System.out.println(question);
        //}
    }
}
