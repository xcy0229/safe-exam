package com.james.mobile;

import com.james.common.constant.MessageConstant;
import com.james.common.enity.QueryPageBean;
import com.james.common.enity.Result;
import com.james.service.SingleChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ACT
 */
@RestController
@RequestMapping("/mobile/single")
public class MobileSingleQuestionController {

    @Autowired
    private SingleChoiceService singleChoiceService;

    @RequestMapping("/getSingle")
    public Result getSingle(@RequestBody QueryPageBean queryPageBean, Integer studentId) {
        try {
            Map<String, Object> data = singleChoiceService.getSingle(queryPageBean, studentId);
            return new Result(true, MessageConstant.SINGLE_CHOICE_QUESTION_QUERY_LIST_TRUE, data);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SINGLE_CHOICE_QUESTION_QUERY_LIST_FALSE);
        }
    }
}
