package com.james.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ACT
 * 收藏集实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collect {

    private Integer id;
    private Integer student_id;
    private Integer question_type;
    private Integer question_id;

    public Collect(Integer student_id, Integer question_type, Integer question_id) {
        this.student_id = student_id;
        this.question_type = question_type;
        this.question_id = question_id;
    }
}
