package com.james.mobile;

import com.james.common.constant.MessageConstant;
import com.james.common.enity.Result;
import com.james.common.pojo.Collect;
import com.james.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ACT
 */
@RestController
@RequestMapping("/mobile/collect")
public class MobileCollectController {

    @Autowired
    private CollectService collectService;

    @RequestMapping("/add")
    public Result add(@RequestBody Collect collect) {
        try {
            boolean isExist = collectService.add(collect);
            if (isExist) {
                return new Result(true, MessageConstant.COLLECT_ADD_TRUE);
            } else {
                return new Result(false, MessageConstant.COLLECT_ADD_REPEAT_FALSE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.COLLECT_ADD_FALSE);
        }
    }

    @RequestMapping("/remove")
    public Result remove(@RequestBody Collect collect) {
        try {
            collectService.remove(collect);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, MessageConstant.COLLECT_DELETE_FALSE);
        }
        return new Result(true, MessageConstant.COLLECT_DELETE_TRUE);
    }

    @RequestMapping("/findAll")
    public Result findAll(Integer studentId) {
        Map<String, Object> result;
        try {
            result = collectService.findAllCollect(studentId);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.COLLECT_QUERY_LIST_FALSE);
        }
        return new Result(true, MessageConstant.COLLECT_QUERY_LIST_TRUE, result);
    }
}
