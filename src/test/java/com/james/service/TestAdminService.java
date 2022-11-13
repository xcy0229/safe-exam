package com.james.service;

import com.james.common.enity.PageResult;
import com.james.common.enity.QueryPageBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author ACT
 */
@SpringBootTest
public class TestAdminService {

    @Autowired
    private AdminService adminService;

    @Test
    void findPages() {
        int currentPage = 1;
        int pageSize = 3;
        String queryString = "";
        QueryPageBean queryPageBean = new QueryPageBean(currentPage, pageSize, queryString);
        //PageHelper.startPage(currentPage, pageSize);
        //Page<SingleChoiceQuestion> page = singleChoiceDao.selectByCondition(queryString);
        PageResult pages = adminService.pageQuery(queryPageBean);
        System.out.println(pages.getTotal());
        List<?> rows = pages.getRows();
        for (Object question : rows) {
            System.out.println(question);
        }
    }
}
