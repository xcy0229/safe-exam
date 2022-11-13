package com.james.mobile;

import com.james.common.constant.MessageConstant;
import com.james.common.enity.Result;
import com.james.common.pojo.Student;
import com.james.common.utils.MD5Utils;
import com.james.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ACT
 */
@RestController
@RequestMapping("/mobile/student")
public class MobileStudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/login")
    public Result login(String user_uid, String password) {
        Student student = null;
        try {
            student = studentService.findStudentByUid(user_uid);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.STUDENT_LOGIN_ERROR_FALSE);
        }
        if (!student.getPassword().equals(MD5Utils.md5(MD5Utils.md5(password)))) {
            return new Result(false, MessageConstant.STUDENT_LOGIN_FALSE);
        }
        if (student.getStatus().equals(2)) {
            return new Result(false, MessageConstant.STUDENT_LOGIN_STATUS_FALSE);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("student", student);
        return new Result(true, MessageConstant.STUDENT_LOGIN_TRUE, map);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody Student student) {
        try {
            studentService.editStudent(student);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.STUDENT_EDIT_FALSE);
        }
        return new Result(false, MessageConstant.STUDENT_EDIT_TRUE);
    }

}
