package com.james.mobile;

import com.james.common.constant.MessageConstant;
import com.james.common.enity.Result;
import com.james.service.ExamPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ACT
 */
@RestController
@RequestMapping("/mobile/examPaper")
public class MobileExamPaperController {

    @Autowired
    private ExamPaperService examPaperService;

    @RequestMapping("/getExamPaper")
    public Result getExamPaper() {
        Map<String, Object> data = new HashMap<>();
        try {
            data = examPaperService.getExamPaper();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_EXAM_PAPER_FALSE);
        }
        return new Result(true, MessageConstant.GET_EXAM_PAPER_TRUE, data);
    }

    @RequestMapping("/getTestPaper")
    public Result getTestPaper() {
        Map<String, Object> data = new HashMap<>();
        try {
            data = examPaperService.getTestPaper();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_EXAM_PAPER_FALSE);
        }
        return new Result(true, MessageConstant.GET_EXAM_PAPER_TRUE, data);
    }
}
