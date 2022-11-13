package com.james.dao;

import com.github.pagehelper.Page;
import com.james.common.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ACT
 */
@Mapper
public interface StudentDao {

    /**
     * 通过条件查询
     * @param queryString 条件
     * @return 结果集
     */
    Page<Student> selectByCondition(String queryString);

    /**
     * 添加学生
     * @param student 学生
     */
    void add(Student student);

    /**
     * 删除学生
     * @param id 学生id
     */
    void deleteById(Integer id);

    /**
     * 根据学生id查询学生信息
     * @param id 学生id
     * @return 学生信息
     */
    Student findStudentById(Integer id);

    /**
     * 获取学生总数
     * @return 结果
     */
    Integer getCountById();

    /**
     * 根据学号查询学生
     * @param user_uid 学号
     * @return student
     */
    Student findStudentByUid(String user_uid);

    /**
     * 修改学生完成情况
     * @param user_id 学生id
     */
    void editSituationById(Integer user_id);

    /**
     * 根据学生id修改学生状态
     * @param id 学生id
     * @param status 学生状态
     */
    void editStudentStatus(Integer id, Integer status);

    /**
     * 修改学生信息根据id
     * @param student 学生
     */
    void editStudentById(Student student);
}
