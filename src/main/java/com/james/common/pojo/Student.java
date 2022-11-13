package com.james.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ACT
 * 实体类-学生
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private Integer id;
    private String user_uid;
    private String user_name;
    private String password;
    private String real_name;
    private Integer age;
    /**
     * 1 男生
     * 2 女生
     */
    private Integer sex;
    private String phone;
    private String image_path;
    /**
     * 1 启用
     * 2 禁用
     */
    private Integer status;
    /**
     * 完成情况
     * 1 完成
     * 2 未完成
     */
    private Integer examination_completion;

}
