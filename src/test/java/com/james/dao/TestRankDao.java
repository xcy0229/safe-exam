package com.james.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ACT
 */
@SpringBootTest
public class TestRankDao {

    @Autowired
    private RankDao rankDao;

    @Test
    void getRank() {
        rankDao.getRank();
    }
}
