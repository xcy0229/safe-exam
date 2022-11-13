package com.james;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author ACT
 * 主启动类
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.james.dao")
public class SafeExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(SafeExamApplication.class, args);
    }

}
