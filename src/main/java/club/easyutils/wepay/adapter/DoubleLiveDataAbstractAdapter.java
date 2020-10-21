package club.easyutils.wepay.adapter;

import club.easyutils.wepay.model.doubleLive.DoubleLiveAnalyzeModel;
import club.easyutils.wepay.util.MathUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.lang.Validator;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 双活机制数据适配器
 * @author rainyblossom
 */
public abstract class DoubleLiveDataAbstractAdapter implements DoubleLiveDataAdapter{


    /**
     * 分析器
     * 通过 getAnalyzeData() 方法注入数据
     * @return TreeMap<Integer, String> —> <得分, 地址>
     */
    @Override
    public TreeMap<Integer, String> analyzer() {
        TreeMap<Integer, String> resultMap = new TreeMap<>();
        List<Double> timeConsumingList = new ArrayList<>();
        Map<String, Integer> scoreMap = new HashMap<>();
        // 获取信心值（中位数）
        for (DoubleLiveAnalyzeModel analyzeModel : this.getAnalyzeData()){
            if (Validator.isNotNull(analyzeModel.getTimeConsuming())){
                timeConsumingList.add(analyzeModel.getTimeConsuming());
            }

        }
        Double mediumCount = MathUtil.getMediumCount(timeConsumingList);

        // 计算得分
        for (DoubleLiveAnalyzeModel analyzeModel : this.getAnalyzeData()){
            Double timeConsuming = analyzeModel.getTimeConsuming();
            if (Validator.isNotNull(timeConsuming)){
                if (timeConsuming <= mediumCount){
                    Integer score = scoreMap.get(analyzeModel.getUrl());
                    score = Validator.isNull(score) ? 1 : score ++;
                    scoreMap.put(analyzeModel.getUrl(), score);
                }
            }
        }

        // 转化结果
        for (Map.Entry<String, Integer> entry : scoreMap.entrySet()){
            resultMap.put(entry.getValue(), entry.getKey());
        }
        return resultMap;
    }
}
