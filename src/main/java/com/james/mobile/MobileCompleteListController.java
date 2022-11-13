package com.james.mobile;

import com.james.common.constant.MessageConstant;
import com.james.common.enity.Result;
import com.james.common.pojo.StudentExamSituation;
import com.james.service.CompleteListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ACT
 */
@RestController
@RequestMapping("/mobile/completeList")
public class MobileCompleteListController {

    @Autowired
    private CompleteListService completeListService;

    @RequestMapping("/add")
    public Result add(@RequestBody StudentExamSituation studentExamSituation) {
        Integer id;
        try {
            id = completeListService.add(studentExamSituation);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_EXAM_SHEET_FALSE);
        }
        return new Result(true, MessageConstant.ADD_EXAM_SHEET_TRUE, id);
    }

    @RequestMapping("/findExamSheetById")
    public Result findExamSheetById(Integer id) {
        StudentExamSituation studentExamSituation;
        try {
            studentExamSituation = completeListService.findExamSheetById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_EXAM_SHEET_FALSE);
        }
        return new Result(true, MessageConstant.GET_EXAM_SHEET_TRUE, studentExamSituation);
    }

    @RequestMapping("/getExamSheet")
    public Result getExamPaper(Integer id) {
        List<StudentExamSituation> examSheet;
        try {
            examSheet = completeListService.getExamPaper(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_EXAM_SHEET_FALSE);
        }
        return new Result(true, MessageConstant.GET_EXAM_SHEET_TRUE, examSheet);
    }
}
