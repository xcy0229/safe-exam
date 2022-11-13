package com.james.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ACT
 * 实体类-多选题
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultipleChoiceQuestions implements Serializable {

    private Integer multiple_id;
    private String multiple_info;
    private String multiple_option_A;
    private String multiple_option_B;
    private String multiple_option_C;
    private String multiple_option_D;
    private String multiple_option_E;
    private String multiple_option_F;
    private String multiple_answer;

    public MultipleChoiceQuestions(String multiple_info, String multiple_option_A, String multiple_option_B, String multiple_option_C, String multiple_option_D, String multiple_option_E, String multiple_option_F, String multiple_answer) {
        this.multiple_info = multiple_info;
        this.multiple_option_A = multiple_option_A;
        this.multiple_option_B = multiple_option_B;
        this.multiple_option_C = multiple_option_C;
        this.multiple_option_D = multiple_option_D;
        this.multiple_option_E = multiple_option_E;
        this.multiple_option_F = multiple_option_F;
        this.multiple_answer = multiple_answer;
    }
}
