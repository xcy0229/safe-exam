package com.james.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.james.dao.AdminDao;
import com.james.common.enity.PageResult;
import com.james.common.enity.QueryPageBean;
import com.james.common.pojo.Admin;
import com.james.service.AdminService;
import com.james.common.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ACT
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public void add(Admin admin) {
        admin.setPassword(MD5Utils.md5(MD5Utils.md5(admin.getPassword())));
        adminDao.add(admin);
    }

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage, pageSize);
        Page<Admin> page = adminDao.selectByCondition(queryString);
        List<Admin> adminList = page.getResult();
        adminList.remove(0);
        return new PageResult(page.getTotal(), adminList);
    }

    @Override
    public void edit(Admin admin) {
        admin.setPassword(MD5Utils.md5(MD5Utils.md5(admin.getPassword())));
        adminDao.edit(admin);
    }

    @Override
    public void delete(Integer id) {
        adminDao.deleteById(id);
    }

    @Override
    public Admin findByUsername(String username) {
        return adminDao.findByUsername(username);
    }

    @Override
    public void editAdminStatus(Integer id, Integer status) {
        adminDao.editAdminStatus(id, status);
    }
}
