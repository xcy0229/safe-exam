package com.james.controller;

import com.james.common.constant.MessageConstant;
import com.james.common.enity.PageResult;
import com.james.common.enity.QueryPageBean;
import com.james.common.enity.Result;
import com.james.common.pojo.Student;
import com.james.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author ACT
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/findPages")
    public PageResult findPages(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = null;
        try {
            pageResult = studentService.pageQuery(queryPageBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageResult;
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Student student) {
        try {
            studentService.add(student);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.STUDENT_ADD_FALSE);
        }
        return new Result(true, MessageConstant.STUDENT_ADD_TRUE);
    }

    @RequestMapping("/editStudentStatus")
    public Result editStudentStatus(Integer id, Integer status) {
        try {
            studentService.editStudentStatus(id, status);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.STUDENT_EDIT_STATUS_FALSE);
        }
        return new Result(true, MessageConstant.STUDENT_EDIT_STATUS_TRUE);
    }

    @RequestMapping("/batchAdd")
    public Result batchAdd(@RequestBody Map<String, String> map) {
        String user_start_uid = map.get("user_start_uid");
        String user_end_uid = map.get("user_end_uid");
        try {
            studentService.batchAdd(user_start_uid, user_end_uid);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.STUDENT_BATCH_ADD_FALSE);
        }
        return new Result(true, MessageConstant.STUDENT_BATCH_ADD_TRUE);
    }

    @RequestMapping("/delete")
    public Result delete(Integer id) {
        try {
            studentService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.STUDENT_DELETE_FALSE);
        }
        return new Result(true, MessageConstant.STUDENT_DELETE_TRUE);
    }

    @RequestMapping("/findStudent")
    public Result findStudent(Integer id) {
        List<Student> list = null;
        try {
            list = studentService.findStudent(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.STUDENT_QUERY_INFO_FALSE);
        }
        return new Result(true, MessageConstant.STUDENT_QUERY_INFO_TRUE, list);
    }

}
