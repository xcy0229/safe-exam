package com.james.controller;

import com.james.common.constant.MessageConstant;
import com.james.common.enity.PageResult;
import com.james.common.enity.QueryPageBean;
import com.james.common.enity.Result;
import com.james.common.pojo.Admin;
import com.james.common.utils.MD5Utils;
import com.james.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author ACT
 * 管理员控制层
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public Result login(@RequestBody Admin user, HttpSession httpSession) {
        if (null == user) {
            return new Result(false, MessageConstant.ADMIN_LOGIN_NULL_FALSE);
        }
        Admin admin = null;
        try {
            admin = adminService.findByUsername(user.getName());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADMIN_LOGIN_ERROR_FALSE);
        }
        if (admin.getStatus().equals(MessageConstant.STATUS)) {
            return new Result(false, MessageConstant.ADMIN_LOGIN_STATUS_FALSE);
        }
        if (!admin.getPassword().equals(MD5Utils.md5(MD5Utils.md5(user.getPassword())))) {
            return new Result(false, MessageConstant.ADMIN_LOGIN_FALSE);
        }
        httpSession.setAttribute("admin", admin);
        return new Result(true, MessageConstant.ADMIN_LOGIN_TRUE);
    }

    @RequestMapping("/getName")
    public Result getName(HttpSession httpSession) {
        Admin admin = (Admin) httpSession.getAttribute("admin");
        String name = admin.getName();
        return new Result(true, MessageConstant.GET_ADMIN_NAME_TRUE, name);
    }

    @RequestMapping("/logout")
    public Result logout(HttpSession httpSession) {
        httpSession.removeAttribute("admin");
        return new Result(true, MessageConstant.ADMIN_LOGOUT_TRUE);
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Admin admin) {
        try {
            if (adminService.findByUsername(admin.getName()) != null) {
                return new Result(false, MessageConstant.ADMIN_ADD_REPEAT_FALSE);
            }
            adminService.add(admin);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADMIN_ADD_FALSE);
        }
        return new Result(true, MessageConstant.ADMIN_ADD_TRUE);
    }

    @RequestMapping("/findPages")
    public PageResult findPages(@RequestBody QueryPageBean queryPageBean) {
        PageResult result = null;
        try {
            result = adminService.pageQuery(queryPageBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody Admin admin) {
        try {
            adminService.edit(admin);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADMIN_EDIT_FALSE);
        }
        return new Result(true, MessageConstant.ADMIN_EDIT_TRUE);
    }

    @RequestMapping("/editAdminStatus")
    public Result editAdminStatus(Integer id, Integer status) {
        try {
            adminService.editAdminStatus(id, status);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADMIN_EDIT_STATUS_FALSE);
        }
        return new Result(true, MessageConstant.ADMIN_EDIT_STATUS_TRUE);
    }

    @RequestMapping("/delete")
    public Result delete(Integer id) {
        try {
            adminService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADMIN_DELETE_FALSE);
        }
        return new Result(true, MessageConstant.ADMIN_DELETE_TRUE);
    }

}
