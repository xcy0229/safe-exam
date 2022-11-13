package com.james.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ACT
 * 实体类-试卷关联的问题
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamQuestion implements Serializable {

    private Integer id;
    private Integer exam_paper_id;
    private Integer exam_question_type;
    private Integer question_id;
    private String answer;
    private Integer question_score;

    private SingleChoiceQuestion singleChoiceQuestion;
    private MultipleChoiceQuestions multipleChoiceQuestions;
    private JudgmentQuestion judgmentQuestion;
}
