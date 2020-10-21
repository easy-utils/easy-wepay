package club.easyutils.wepay.handler.doubleLive;

import club.easyutils.wepay.adapter.DoubleLiveDataAdapter;
import club.easyutils.wepay.constant.GuavaConstant;
import club.easyutils.wepay.handler.BaseHandler;
import cn.hutool.core.date.StopWatch;
import cn.hutool.core.lang.Validator;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Host 灾备双活入口处理器
 * @author rainyblossom
 */
@Service
public class WePayHostDoubleLiveHandler extends BaseHandler {

    @Autowired(required = false)
    private DoubleLiveDataAdapter doubleLiveDataAdapter;

    @Setter
    @Getter
    private List<String> hosts;

    public String getHost(){
        String value = hosts.stream().findFirst().get();
        Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(1).expireAfterWrite(7200, TimeUnit.SECONDS).build();
        try {
            value =  cache.get(GuavaConstant.WEPAY_CACHE_HOST.getCacheName(), () -> calculateUsability());
        } catch (ExecutionException e) {
            logger.error("TokenService Failed.");
        }
        return value;
    }


    /**
     * 计算可用性
     * @return Map<Integer, String> —> <可用性, HostUrl>（按照可用性高低排序）
     */
    private String calculateUsability(){
        List<String> whiteHost = new ArrayList<>();

        // 默认可用性规则：探测接口速度
        TreeMap<Long, String> hostTelnetMap = telnet(hosts, whiteHost);

        // 计算历史请求可用性
        TreeMap<Integer, String> analyzerMap = doubleLiveDataAdapter.analyzer();

        // 合并计算最优解
        return rebuild(hostTelnetMap, analyzerMap, whiteHost);

    }

    /**
     * 合并计算最优解
     * @param hostTelnetMap
     * @param analyzerMap
     * @param whiteHost
     * @return
     */
    private String rebuild(TreeMap<Long, String> hostTelnetMap, TreeMap<Integer, String> analyzerMap, List<String> whiteHost) {
        if (Validator.isNull(analyzerMap) || Validator.isEmpty(analyzerMap)){
            return hostTelnetMap.firstEntry().getValue();
        }
        for (Map.Entry<Integer, String> entry : analyzerMap.entrySet()){
            if (whiteHost.contains(entry.getValue())){
                return entry.getValue();
            }
        }
        return hostTelnetMap.firstEntry().getValue();
    }

    private TreeMap<Long, String> telnet(List<String> hosts, List<String> whiteHost) {
        TreeMap<Long, String> hostTelnetMap = new TreeMap<>();
        StopWatch stopWatch = new StopWatch();
        Socket socket = new Socket();
        for (String host :hosts){
            stopWatch.start();
            try {
                socket.connect(new InetSocketAddress(host, 80), 2000);
            } catch (IOException e) {
                logger.error("【接口探测异常】探测 Socket 连接异常，异常日志：{}", e.getStackTrace());
            }finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    logger.error("【接口探测异常】探测 Socket 关闭异常，异常日志：{}", e.getStackTrace());
                }
            }
            stopWatch.stop();
            if (socket.isConnected()){
                whiteHost.add(host);
                hostTelnetMap.put(stopWatch.getLastTaskTimeNanos(), host);
                logger.info("【接口探测日志】当前 Host {} 耗时 {} 纳秒", host, stopWatch.getLastTaskTimeNanos());
            }else {
                logger.warn("【接口探测警告】当前 Host {} 不可用", host);
            }
        }
        return hostTelnetMap;
    }


}
