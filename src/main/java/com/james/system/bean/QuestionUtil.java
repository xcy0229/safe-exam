package com.james.system.bean;

import com.james.common.enity.Option;
import com.james.common.pojo.Collect;
import com.james.common.pojo.JudgmentQuestion;
import com.james.common.pojo.MultipleChoiceQuestions;
import com.james.common.pojo.SingleChoiceQuestion;
import com.james.dao.CollectDao;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author ACT
 * 题目处理工具类
 */
@Component
public class QuestionUtil {

    @Autowired
    private CollectDao collectDao;

    public List<Map<String, Object>> singleUtil(List<SingleChoiceQuestion> singleQuestion, Integer studentId) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (SingleChoiceQuestion question : singleQuestion) {
            Map<String, Object > map = new HashMap<>();
            List<Option> options = new ArrayList<>();
            if (null != question.getChoice_option_A()) {
                options.add(newOption(question.getChoice_option_A()));
            }
            if (null != question.getChoice_option_B()) {
                options.add(newOption(question.getChoice_option_B()));
            }
            if (null != question.getChoice_option_C()) {
                options.add(newOption(question.getChoice_option_C()));
            }
            if (null != question.getChoice_option_D()) {
                options.add(newOption(question.getChoice_option_D()));
            }
            map.put("id", question.getChoice_id());
            map.put("title", question.getChoice_info());
            map.put("options", options);
            map.put("answer", question.getChoice_answer().toCharArray());
            map.put("type", "single");
            map.put("question_type", 1);
            map.put("typeText", "单选题");
            boolean collect = getCollect(new Collect(studentId, 1, question.getChoice_id()));
            if (collect) {
                // 已添加收藏
                map.put("starred", true);
            } else {
                map.put("starred", false);
            }
            result.add(map);
        }
        return result;
    }

    public List<Map<String, Object>> judgmentUtil(List<JudgmentQuestion> judgmentQuestions, Integer studentId) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (JudgmentQuestion question : judgmentQuestions) {
            Map<String, Object > map = new HashMap<>();
            List<Option> options = new ArrayList<>();
            options.add(new Option("A", "对"));
            options.add(new Option("B", "错"));
            map.put("id", question.getJudgment_id());
            map.put("title", question.getJudgment_info());
            map.put("options", options);
            map.put("answer", (question.getJudgment_answer() == 1 ? "A" : "B").toCharArray());
            map.put("type", "judgment");
            map.put("question_type", 3);
            map.put("typeText", "判断题");
            boolean collect = getCollect(new Collect(studentId, 3, question.getJudgment_id()));
            if (collect) {
                // 已添加收藏
                map.put("starred", true);
            } else {
                map.put("starred", false);
            }
            result.add(map);
        }
        return result;
    }

    public List<Map<String, Object>> multipleUtil(List<MultipleChoiceQuestions> multipleQuestion, Integer studentId) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (MultipleChoiceQuestions question : multipleQuestion) {
            Map<String, Object > map = new HashMap<>();
            List<Option> options = new ArrayList<>();
            if (null != question.getMultiple_option_A()) {
                options.add(newOption(question.getMultiple_option_A()));
            }
            if (null != question.getMultiple_option_B()) {
                options.add(newOption(question.getMultiple_option_B()));
            }
            if (null != question.getMultiple_option_C()) {
                options.add(newOption(question.getMultiple_option_C()));
            }
            if (null != question.getMultiple_option_D()) {
                options.add(newOption(question.getMultiple_option_D()));
            }
            if (null != question.getMultiple_option_E()) {
                options.add(newOption(question.getMultiple_option_E()));
            }
            if (null != question.getMultiple_option_F()) {
                options.add(newOption(question.getMultiple_option_F()));
            }
            map.put("id", question.getMultiple_id());
            map.put("title", question.getMultiple_info());
            map.put("options", options);
            map.put("answer", question.getMultiple_answer().toCharArray());
            map.put("type", "multiple");
            map.put("question_type", 2);
            map.put("typeText", "多选题");
            boolean collect = getCollect(new Collect(studentId, 2, question.getMultiple_id()));
            if (collect) {
                // 已添加收藏
                map.put("starred", true);
            } else {
                map.put("starred", false);
            }
            result.add(map);
        }
        return result;
    }

    private Option newOption(String option) {
        return new Option(String.valueOf(option.charAt(0)), option.substring(1, option.length()));
    }


    public String answerSort(String answer) {
        char[] chars = answer.replace(" ", "").toCharArray();
        List<String> list = new ArrayList<>();
        for (char aChar : chars) {
            list.add(String.valueOf(aChar));
        }
        Collections.sort(list);
        String result = StringUtils.join(list).replace(",", "");
        return result;
    }

    private boolean getCollect(Collect collect) {
        Collect isExist = collectDao.findCollectByAllId(collect);
        return null != isExist;
    }
}
