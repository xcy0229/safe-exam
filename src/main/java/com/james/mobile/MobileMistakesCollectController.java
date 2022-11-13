package com.james.mobile;

import com.james.common.constant.MessageConstant;
import com.james.common.domain.MisQuestion;
import com.james.common.enity.Result;
import com.james.service.MistakesCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ACT
 */
@RestController
@RequestMapping("/mobile/mistakes")
public class MobileMistakesCollectController {

    @Autowired
    private MistakesCollectService mistakesCollectService;

    @RequestMapping("/add")
    public Result add(@RequestBody MisQuestion misQuestion) {
        try {
            mistakesCollectService.add(misQuestion);
            return new Result(true, MessageConstant.MISTAKES_COLLECT_ADD_TRUE);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.MISTAKES_COLLECT_ADD_FALSE);
        }
    }

    @RequestMapping("/findAll")
    public Result findAll(Integer studentId) {
        Map<String, Object> result;
        try {
            result = mistakesCollectService.findAllCollect(studentId);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.MISTAKES_COLLECT_QUERY_LIST_FALSE);
        }
        return new Result(true, MessageConstant.MISTAKES_COLLECT_QUERY_LIST_TRUE, result);
    }
}
