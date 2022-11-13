package com.james.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.james.dao.StudentDao;
import com.james.common.enity.PageResult;
import com.james.common.enity.QueryPageBean;
import com.james.common.pojo.Student;
import com.james.service.StudentService;
import com.james.common.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ACT
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage, pageSize);
        Page<Student> page = studentDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void add(Student student) {
        student.setPassword(MD5Utils.md5(MD5Utils.md5("123456")));
        studentDao.add(student);
    }

    @Override
    public void batchAdd(String user_start_uid, String user_end_uid) {
        Student student = new Student();
        student.setPassword(MD5Utils.md5(MD5Utils.md5("123456")));
        student.setStatus(1);
        int startId = Integer.parseInt(user_start_uid);
        int endId = Integer.parseInt(user_end_uid);
        for (int i = startId; i <= endId; i++) {
            student.setUser_uid(String.valueOf(i));
            studentDao.add(student);
        }
    }

    @Override
    public void delete(Integer id) {
        studentDao.deleteById(id);
    }

    @Override
    public List<Student> findStudent(Integer id) {
        List<Student> studentList = new ArrayList<>();
        Student student = studentDao.findStudentById(id);
        studentList.add(student);
        return studentList;
    }

    @Override
    public Student findStudentByUid(String user_uid) {
        return studentDao.findStudentByUid(user_uid);
    }

    @Override
    public void editStudentStatus(Integer id, Integer status) {
        studentDao.editStudentStatus(id, status);
    }

    @Override
    public void editStudent(Student student) {
        studentDao.editStudentById(student);
    }
}
