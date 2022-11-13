package com.james.controller;

import com.james.common.constant.MessageConstant;
import com.james.common.enity.PageResult;
import com.james.common.enity.QueryPageBean;
import com.james.common.enity.Result;
import com.james.common.pojo.MultipleChoiceQuestions;
import com.james.service.MultipleChoiceQuestionService;
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
@RequestMapping("/multipleChoice")
public class MultipleChoiceQuestionController {

    @Autowired
    private MultipleChoiceQuestionService multipleChoiceQuestionService;

    @RequestMapping("/add")
    public Result add(@RequestBody MultipleChoiceQuestions multipleChoiceQuestions) {
        try {
            multipleChoiceQuestionService.add(multipleChoiceQuestions);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.MULTIPLE_QUESTION_ADD_FALSE);
        }
        return new Result(true, MessageConstant.MULTIPLE_QUESTION_ADD_TRUE);
    }

    @RequestMapping("/findPages")
    public PageResult findPages(@RequestBody QueryPageBean queryPageBean) {
        PageResult result = null;
        try {
            result = multipleChoiceQuestionService.findPages(queryPageBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody MultipleChoiceQuestions multipleChoiceQuestions) {
        try {
            multipleChoiceQuestionService.edit(multipleChoiceQuestions);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.MULTIPLE_QUESTION_EDIT_FALSE);
        }
        return new Result(true, MessageConstant.MULTIPLE_QUESTION_EDIT_TRUE);
    }

    @RequestMapping("/delete")
    public Result delete(Integer id) {
        try {
            multipleChoiceQuestionService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.MULTIPLE_QUESTION_DELETE_FALSE);
        }
        return new Result(true, MessageConstant.MULTIPLE_QUESTION_DELETE_TRUE);
    }

    @RequestMapping("/findMultipleChoice")
    public Result findMultipleChoice(Integer id) {
        List<MultipleChoiceQuestions> list = new ArrayList<>();
        try {
            list = multipleChoiceQuestionService.findMultipleChoice(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.MULTIPLE_QUESTION_QUERY_INFO_FALSE);
        }
        return new Result(true, MessageConstant.MULTIPLE_QUESTION_QUERY_INFO_TRUE, list);
    }
}
