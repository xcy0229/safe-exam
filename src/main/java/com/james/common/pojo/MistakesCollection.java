package com.james.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ACT
 * 错题本实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MistakesCollection {

    private Integer id;
    private Integer student_id;
    private Integer question_type;
    private Integer question_id;
    private String student_answer;
}
