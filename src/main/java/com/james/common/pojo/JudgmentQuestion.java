package com.james.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ACT
 * 实体类-判断题
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JudgmentQuestion implements Serializable {

    private Integer judgment_id;
    private String judgment_info;
    private Integer judgment_answer;

    public JudgmentQuestion(String judgment_info, Integer judgment_answer) {
        this.judgment_info = judgment_info;
        this.judgment_answer = judgment_answer;
    }
}
