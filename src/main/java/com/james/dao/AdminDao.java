package com.james.dao;

import com.github.pagehelper.Page;
import com.james.common.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author ACT
 */
@Mapper
public interface AdminDao {

    /**
     * 添加管理员
     * @param admin 管理员
     */
    void add(Admin admin);

    /**
     * 根据条件查询
     * @param queryString 查询条件
     * @return 结果集
     */
    Page<Admin> selectByCondition(String queryString);

    /**
     * 修改管理员信息
     * @param admin 管理员
     */
    void edit(Admin admin);

    /**
     * 根据id删除管理员
     * @param id 管理员id
     */
    void deleteById(Integer id);

    /**
     * 根据用户名查询用户
     * @param name 用户名
     * @return admin
     */
    Admin findByUsername(String name);

    /**
     * 根据管理员id修改状态
     * @param id 管理员id
     * @param status 管理员状态
     */
    void editAdminStatus(Integer id, Integer status);
}
