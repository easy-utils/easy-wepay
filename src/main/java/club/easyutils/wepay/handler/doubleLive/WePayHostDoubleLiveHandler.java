package club.easyutils.wepay.handler.doubleLive;

import club.easyutils.wepay.constant.GuavaConstant;
import club.easyutils.wepay.handler.BaseHandler;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Host 灾备双活入口处理器
 * @author rainyblossom
 */
@Service
public class WePayHostDoubleLiveHandler extends BaseHandler {

    @Setter
    @Getter
    private List<String> hosts;

    public String getHost(){
        String value = hosts.stream().findFirst().get();
        Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(1).expireAfterWrite(7200, TimeUnit.SECONDS).build();
        try {
            value =  cache.get(GuavaConstant.WEPAY_CACHE_HOST.getCacheName(), () -> calculateUsability().firstEntry().getValue());
        } catch (ExecutionException e) {
            logger.error("TokenService Failed.");
        }
        return value;
    }


    /**
     * 计算可用性
     * @return Map<Integer, String> —> <可用性, HostUrl>（按照可用性高低排序）
     */
    private TreeMap<Integer, String> calculateUsability(){
        TreeMap<Integer, String> hostsMap= new TreeMap<>();
        hostsMap.put(1, hosts.get(0));
        return hostsMap;
    }


}
