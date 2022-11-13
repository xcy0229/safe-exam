package com.james.controller;

import com.james.common.enity.PageResult;
import com.james.common.enity.QueryPageBean;
import com.james.service.CompleteListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ACT
 */
@RestController
@RequestMapping("/completeList")
public class CompleteListController {

    @Autowired
    private CompleteListService completeListService;

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult result = null;
        try {
            result = completeListService.pageQuery(queryPageBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
