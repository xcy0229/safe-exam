package com.james.controller;

import com.james.common.constant.MessageConstant;
import com.james.common.enity.PageResult;
import com.james.common.enity.QueryPageBean;
import com.james.common.enity.Result;
import com.james.common.pojo.JudgmentQuestion;
import com.james.service.JudgmentQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ACT
 */
@RestController
@RequestMapping("/judgment")
public class JudgmentQuestionController {

    @Autowired
    private JudgmentQuestionService judgmentQuestionService;

    @RequestMapping("/add")
    public Result add(@RequestBody JudgmentQuestion judgmentQuestion) {
        try {
            judgmentQuestionService.add(judgmentQuestion);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.JUDGMENT_QUESTION_ADD_FALSE);
        }
        return new Result(true, MessageConstant.JUDGMENT_QUESTION_ADD_TRUE);
    }

    @RequestMapping("/findPages")
    public PageResult findPages(@RequestBody QueryPageBean queryPageBean) {
        PageResult result = null;
        try {
            result = judgmentQuestionService.findPages(queryPageBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody JudgmentQuestion judgmentQuestion) {
        try {
            judgmentQuestionService.edit(judgmentQuestion);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.JUDGMENT_QUESTION_EDIT_FALSE);
        }
        return new Result(true, MessageConstant.JUDGMENT_QUESTION_EDIT_TRUE);
    }

    @RequestMapping("/findJudgment")
    public Result findJudgment(Integer id) {
        List<JudgmentQuestion> list = new ArrayList<>();
        try {
            list = judgmentQuestionService.findJudgmentInfo(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.JUDGMENT_QUESTION_QUERY_INFO_FALSE);
        }
        return new Result(true, MessageConstant.JUDGMENT_QUESTION_QUERY_INFO_TRUE, list);
    }

    @RequestMapping("/delete")
    public Result delete(Integer id) {
        try {
            judgmentQuestionService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.JUDGMENT_QUESTION_DELETE_FALSE);
        }
        return new Result(true, MessageConstant.JUDGMENT_QUESTION_DELETE_TRUE);
    }

}
