package club.easyutils.wepay.adapter;

import club.easyutils.wepay.model.doubleLive.DoubleLiveAnalyzeModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TreeMap;

/**
 * 双活机制数据适配器
 * @author rainyblossom
 */
public interface DoubleLiveDataAdapter {

    /**
     * 分析器
     * @return
     */
    TreeMap<Integer, String> analyzer();

    /**
     * 获取基础分析数据
     * 调用方实现，注入历史支付请求数据
     * @return
     */
    List<DoubleLiveAnalyzeModel> getAnalyzeData();




}
