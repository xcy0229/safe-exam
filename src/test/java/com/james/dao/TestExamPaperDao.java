package com.james.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author ACT
 */
@SpringBootTest
public class TestExamPaperDao {

    @Autowired
    private ExamPaperDao examPaperDao;

    @Test
    void getExamPaperId() {
        List<Integer> examPaperId = examPaperDao.getExamPaperId();
        System.out.println(examPaperId);
    }

    @Test
    void getTestPaperId() {
        System.out.println(examPaperDao.getTestPaperId());
    }
}
