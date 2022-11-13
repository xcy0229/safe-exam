package com.james.service.impl;

import com.james.common.pojo.StudentExamSituation;
import com.james.dao.RankDao;
import com.james.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ACT
 */
@Service
@Transactional
public class RankServiceImpl implements RankService {

    @Autowired
    private RankDao rankDao;

    @Override
    public List<StudentExamSituation> getRank() {
        return rankDao.getRank();
    }
}
