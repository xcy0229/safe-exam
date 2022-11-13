package com.james.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ACT
 * 实体类-单选题
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleChoiceQuestion implements Serializable {

    private Integer choice_id;
    private String choice_info;
    private String choice_option_A;
    private String choice_option_B;
    private String choice_option_C;
    private String choice_option_D;
    private String choice_answer;

    public SingleChoiceQuestion(String choice_info, String choice_option_A, String choice_option_B, String choice_option_C, String choice_option_D, String choice_answer) {
        this.choice_info = choice_info;
        this.choice_option_A = choice_option_A;
        this.choice_option_B = choice_option_B;
        this.choice_option_C = choice_option_C;
        this.choice_option_D = choice_option_D;
        this.choice_answer = choice_answer;
    }
}
