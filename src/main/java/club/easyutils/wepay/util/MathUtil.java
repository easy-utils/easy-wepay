package club.easyutils.wepay.util;

import java.util.Collections;
import java.util.List;

/**
 * 数学工具
 * @author rainyblossom
 */
public class MathUtil {

    /**
     * 获取中位数
     */
    public static Double getMediumCount(List<Double> sortList){
        double result = 0;
        //集合排序
        Collections.sort(sortList);
        if(sortList.size() % 2 == 1){
            result = sortList.get((sortList.size()-1)/2);
        }else {
            result = (sortList.get(sortList.size()/2-1) + sortList.get(sortList.size()/2) + 0.0)/2;
        }
        return result;
    }
}
