package com.james.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ACT
 * 实体类-管理员
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    private Integer id;
    private String name;
    private String password;
    private String real_name;
    private Integer sex;
    private String phone;
    private String image_path;
    /**
     * 账户状态
     * 1.启用
     * 2.禁用
     */
    private Integer status;
}
