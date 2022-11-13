package com.james.controller;

import com.james.common.constant.MessageConstant;
import com.james.common.enity.Result;
import com.james.common.pojo.JudgmentQuestion;
import com.james.common.pojo.MultipleChoiceQuestions;
import com.james.common.pojo.SingleChoiceQuestion;
import com.james.common.utils.POIUtils;
import com.james.service.JudgmentQuestionService;
import com.james.service.MultipleChoiceQuestionService;
import com.james.service.SingleChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ACT
 * 处理题目上传
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private SingleChoiceService singleService;

    @Autowired
    private JudgmentQuestionService judgmentService;

    @Autowired
    private MultipleChoiceQuestionService multipleService;

    @RequestMapping("/upload")
    public Result upload(@RequestParam("excelFile") MultipartFile excelFile) {
        try {
            //获取Excel文件数据
            List<String[]> excel = POIUtils.readExcel(excelFile);
            if (excel.size() > 0) {
                //将excel内容转化为各自的题目对象
                List<SingleChoiceQuestion> singleList = new ArrayList<>();
                List<JudgmentQuestion> judgmentList = new ArrayList<>();
                List<MultipleChoiceQuestions> multipleList = new ArrayList<>();
                for (String[] strings : excel) {
                    if ("单选题".equals(strings[0])) {
                        // 转化为单选题对象
                        SingleChoiceQuestion singleQuestion =
                                new SingleChoiceQuestion(strings[1], strings[2], strings[3], strings[4], strings[5], strings[8]);
                        singleService.add(singleQuestion);
                    } else if ("多选题".equals(strings[0])) {
                        // 转化为多选题对象
                        MultipleChoiceQuestions multipleQuestion =
                                new MultipleChoiceQuestions(strings[1],
                                        strings[2], strings[3], strings[4], strings[5], strings[6], strings[7],
                                        strings[8]);
                        multipleService.add(multipleQuestion);
                    } else if ("判断题".equals(strings[0])) {
                        // 转化为判断题对象
                        JudgmentQuestion judgmentQuestion =
                                new JudgmentQuestion(strings[1], "A".equals(strings[8]) ? 1 : 2);
                        judgmentService.add(judgmentQuestion);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.UPLOAD_FAIL);
        }
        return new Result(true, MessageConstant.UPLOAD_TRUE);
    }
}
