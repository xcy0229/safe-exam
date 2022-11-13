package com.james.dao;

import com.james.common.pojo.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ACT
 *
 */
@SpringBootTest
public class TestAdminDao {

    @Autowired
    private AdminDao adminDao;

    @Test
    void insert() {
        Admin admin = new Admin();
        admin.setName("王八");
        admin.setPassword("123456");
        admin.setStatus(1);
        //adminDao.insert(admin);
    }

    @Test
    void selectPage() {
        //QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        //wrapper.like("name", "a");
        //Page<Admin> page = new Page<>(1, 3);
        //page = adminDao.selectPage(page, wrapper);
    }

    @Test
    void update() {
        Admin admin = new Admin();
        admin.setId(4);
        admin.setName("李明");
        admin.setPassword("123");
        admin.setStatus(1);
        //adminDao.updateById(admin);
    }

    @Test
    void delete() {
        adminDao.deleteById(2);
    }

    @Test
    void selectOne() {
        Admin admin = new Admin();
        admin.setName("root");
        //QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        //wrapper.setEntity(admin);
        //adminDao.selectOne(wrapper);
    }

    @Test
    void selectByCondition() {
        adminDao.selectByCondition("李");
    }
}
