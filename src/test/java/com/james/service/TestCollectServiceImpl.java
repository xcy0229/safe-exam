package com.james.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ACT
 */
@SpringBootTest
public class TestCollectServiceImpl {

    @Autowired
    private CollectService collectService;

    @Test
    void findAllCollect() {
        System.out.println(collectService.findAllCollect(1));
    }
}
