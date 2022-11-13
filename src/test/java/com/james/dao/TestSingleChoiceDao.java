package com.james.dao;

import com.github.pagehelper.Page;
import com.james.common.pojo.SingleChoiceQuestion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ACT
 */
@SpringBootTest
public class TestSingleChoiceDao {

    @Autowired
    private SingleChoiceDao singleChoiceDao;

    @Test
    void findAll() {
        singleChoiceDao.findAll();
    }

    @Test
    void selectList() {
        //singleChoiceDao.selectList(null);
    }

    @Test
    void selectByCondition() {
        Page<SingleChoiceQuestion> page = singleChoiceDao.selectByCondition(null);
    }

    @Test
    void add() {
        SingleChoiceQuestion question = new SingleChoiceQuestion();
        question.setChoice_info("暗示的复活节安抚沙发和骄傲时口袋里发多少会计法哈少得可怜打死了房间哈迪斯考拉海购阿时光回溯肯定会阿萨德更好卡拉什福德阿萨德风华高科了和扫地杆犯贱了卡视角弗兰克打算福建省第六届大苦尽甘来解放东路阿根廷如何是技术的覅偶爱洋河股份即保存的集合是为卡回复第");
        question.setChoice_answer("A");
        singleChoiceDao.add(question);
    }
}
