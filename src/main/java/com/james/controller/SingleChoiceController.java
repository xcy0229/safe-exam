package com.james.controller;

import com.james.common.constant.MessageConstant;
import com.james.common.enity.PageResult;
import com.james.common.enity.QueryPageBean;
import com.james.common.enity.Result;
import com.james.common.pojo.SingleChoiceQuestion;
import com.james.service.SingleChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ACT
 */
@RestController
@RequestMapping("/singleChoice")
public class SingleChoiceController {

    @Autowired
    private SingleChoiceService singleChoiceService;

    @RequestMapping("/add")
    public Result add(@RequestBody SingleChoiceQuestion singleChoiceQuestion) {
        try {
            singleChoiceService.add(singleChoiceQuestion);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SINGLE_CHOICE_QUESTION_ADD_FALSE);
        }
        return new Result(true, MessageConstant.SINGLE_CHOICE_QUESTION_ADD_TRUE);
    }

    @RequestMapping("/findPages")
    public PageResult findPages(@RequestBody QueryPageBean queryPageBean) {
        PageResult result = null;
        try {
            result = singleChoiceService.findPages(queryPageBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody SingleChoiceQuestion singleChoiceQuestion) {
        try {
            singleChoiceService.edit(singleChoiceQuestion);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SINGLE_CHOICE_QUESTION_EDIT_FALSE);
        }
        return new Result(true, MessageConstant.SINGLE_CHOICE_QUESTION_EDIT_TRUE);
    }

    @RequestMapping("/findSingleChoice")
    public Result findSingleChoice(Integer id) {
        List<SingleChoiceQuestion> list = null;
        try {
            list = singleChoiceService.findSingleChoiceInfo(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SINGLE_CHOICE_QUESTION_QUERY_INFO_FALSE);
        }
        return new Result(true, MessageConstant.SINGLE_CHOICE_QUESTION_QUERY_INFO_TRUE, list);
    }

    @RequestMapping("/delete")
    public Result delete(Integer id) {
        try {
            singleChoiceService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SINGLE_CHOICE_QUESTION_DELETE_FALSE);
        }
        return new Result(true, MessageConstant.SINGLE_CHOICE_QUESTION_DELETE_TRUE);
    }
}
