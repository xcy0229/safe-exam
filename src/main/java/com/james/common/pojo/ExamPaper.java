package com.james.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author ACT
 * 实体类-试卷
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamPaper implements Serializable {

    private Integer id;
    private String name;
    private Integer score;
    private Integer question_count;
    private Integer default_paper;
    private List<ExamQuestion> questionList;
}
