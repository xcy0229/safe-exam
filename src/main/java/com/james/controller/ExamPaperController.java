package com.james.controller;

import com.james.common.constant.MessageConstant;
import com.james.common.enity.PageResult;
import com.james.common.enity.QueryPageBean;
import com.james.common.enity.Result;
import com.james.common.pojo.ExamPaper;
import com.james.service.ExamPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ACT
 */
@RestController
@RequestMapping("/examPaper")
public class ExamPaperController {

    @Autowired
    private ExamPaperService examPaperService;

    @RequestMapping("/findAll")
    public Result findAll() {
        try {
            List<Object> list = examPaperService.findAll();
            if (list != null && list.size() > 0) {
                return new Result(true, MessageConstant.QUESTION_QUERY_LIST_TRUE, list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUESTION_QUERY_LIST_FALSE);
        }
        return new Result(false, MessageConstant.QUESTION_QUERY_LIST_FALSE);
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult result = null;
        try {
            result = examPaperService.findPage(queryPageBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/delete")
    public Result delete(Integer id) {
        try {
            examPaperService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EXAM_PAPER_DELETE_FALSE);
        }
        return new Result(true, MessageConstant.EXAM_PAPER_DELETE_TRUE);
    }

    @RequestMapping("/add")
    public Result add(Integer[] singleIds, Integer[] multipleIds, Integer[] judgmentIds, @RequestBody ExamPaper examPaper) {
        try {
            examPaperService.add(singleIds, multipleIds, judgmentIds, examPaper);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EXAM_PAPER_ADD_FALSE);
        }
        return new Result(true, MessageConstant.EXAM_PAPER_ADD_TRUE);
    }

    @RequestMapping("/randomAdd")
    public Result randomAdd(@RequestBody ExamPaper examPaper) {
        try {
            examPaperService.randomAdd(examPaper);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EXAM_PAPER_RANDOM_ADD_FALSE);
        }
        return new Result(true, MessageConstant.EXAM_PAPER_RANDOM_ADD_TRUE);
    }

    @RequestMapping("/findQuestionIdsByPaperId")
    public Result findQuestionIdsByPaperId(Integer id) {
        List<List<Integer>> ids;
        try {
            ids = examPaperService.findQuestionIdsByPaperId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUESTION_QUERY_LIST_FALSE);
        }
        return new Result(true, MessageConstant.QUESTION_QUERY_LIST_TRUE, ids);
    }

    @RequestMapping("/edit")
    public Result edit(Integer[] singleIds, Integer[] multipleIds, Integer[] judgmentIds, @RequestBody ExamPaper examPaper) {
        try {
            examPaperService.edit(singleIds, multipleIds, judgmentIds, examPaper);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EXAM_PAPER_EDIT_FALSE);
        }
        return new Result(true, MessageConstant.EXAM_PAPER_EDIT_TRUE);
    }

    @RequestMapping("/defaultPaper")
    public Result defaultPaper(Integer id, Integer status) {
        try {
            examPaperService.editDefault(id, status);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EXAM_PAPER_EDIT_FALSE);
        }
        return new Result(true, MessageConstant.EXAM_PAPER_EDIT_TRUE);
    }

}
