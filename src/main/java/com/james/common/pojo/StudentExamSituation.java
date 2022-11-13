package com.james.common.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ACT
 * 实体类-学生考试信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentExamSituation implements Serializable {

    private Integer user_exam_id;
    private Integer exam_paper_id;
    private String exam_paper_name;
    private Integer user_id;
    private String user_uid;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date start_time;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date complete_time;
    private Integer core;
    private Integer score;

}
