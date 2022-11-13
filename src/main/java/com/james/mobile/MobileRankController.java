package com.james.mobile;

import com.james.common.constant.MessageConstant;
import com.james.common.enity.Result;
import com.james.common.pojo.StudentExamSituation;
import com.james.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ACT
 */
@RestController
@RequestMapping("/mobile/rank")
public class MobileRankController {

    @Autowired
    private RankService rankService;

    @RequestMapping("/getRank")
    public Result getBank() {
        try {
            List<StudentExamSituation> rankList = rankService.getRank();
            return new Result(true, MessageConstant.RANK_QUERY_LIST_TRUE, rankList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.RANK_QUERY_LIST_FALSE);
        }
    }
}
