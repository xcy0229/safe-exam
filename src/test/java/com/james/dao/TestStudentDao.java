package com.james.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ACT
 */
@SpringBootTest
public class TestStudentDao {

    @Autowired
    private StudentDao studentDao;

    @Test
    void selectByCondition() {
        studentDao.selectByCondition("李");
    }
}
