package com.james.service;

import com.james.common.enity.PageResult;
import com.james.common.enity.QueryPageBean;
import com.james.common.pojo.Admin;

/**
 * @author ACT
 */
public interface AdminService {

    /**
     * 添加管理员
     * @param admin 管理员
     */
    void add(Admin admin);

    /**
     * 分页查询
     * @param queryPageBean 查询条件
     * @return 封装结果集
     */
    PageResult pageQuery(QueryPageBean queryPageBean);

    /**
     * 修改管理员信息
     * @param admin 管理员
     */
    void edit(Admin admin);

    /**
     * 删除管理员
     * @param id 管理员id
     */
    void delete(Integer id);

    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return admin
     */
    Admin findByUsername(String username);

    /**
     * 修改管理员状态
     * @param id 管理员id
     * @param status 状态
     */
    void editAdminStatus(Integer id, Integer status);
}
