package com.james.service;

import com.james.common.enity.PageResult;
import com.james.common.enity.QueryPageBean;
import com.james.common.pojo.Student;

import java.util.List;

/**
 * @author ACT
 */
public interface StudentService {

    /**
     * 分页查询
     * @param queryPageBean 查询条件
     * @return 封装结果集
     */
    PageResult pageQuery(QueryPageBean queryPageBean);

    /**
     * 添加学生
     * @param student 学生
     */
    void add(Student student);

    /**
     * 批量创建学生
     * @param user_start_uid 起始学号
     * @param user_end_uid 结束学号
     */
    void batchAdd(String user_start_uid, String user_end_uid);

    /**
     * 删除学生
     * @param id 学生id
     */
    void delete(Integer id);

    /**
     * 根据id查询学生信息
     * @param id 学生id
     * @return 学生信息
     */
    List<Student> findStudent(Integer id);

    /**
     * 学生登录
     * @param user_uid 学号
     * @return 结果
     */
    Student findStudentByUid(String user_uid);

    /**
     * 修改学生状态
     * @param id 学生id
     * @param status 状态信息
     */
    void editStudentStatus(Integer id, Integer status);

    /**
     * 修改学生信息根据id
     * @param student 学生
     */
    void editStudent(Student student);
}
