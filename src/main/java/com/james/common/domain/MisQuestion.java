package com.james.common.domain;

import com.james.common.pojo.MistakesCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ACT
 * 错题本集合参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MisQuestion {

    private List<MistakesCollection> misSingle;
    private List<MistakesCollection> misJudgment;
    private List<MistakesCollection> misMultiple;

}
