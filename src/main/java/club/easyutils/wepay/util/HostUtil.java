package club.easyutils.wepay.util;

import club.easyutils.wepay.config.BaseHostConfig;
import club.easyutils.wepay.handler.doubleLive.WePayHostDoubleLiveHandler;
import cn.hutool.core.lang.Validator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class HostUtil {

    @Autowired(required = false)
    private WePayHostDoubleLiveHandler doubleLiveHandler;

    private static HostUtil hostUtil;

    @PostConstruct
    public void init() {
        hostUtil = this;
    }

    /**
     * 微信支付双活入口配置工具
     *
     * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=23_6&index=4
     * @return
     */
    public static String getDoubleLiveHost(){
        if (Validator.isNotNull(hostUtil.doubleLiveHandler)){
            return hostUtil.doubleLiveHandler.getHost();
        }
        // 返回默认域名
        return BaseHostConfig.WEPAY_HOST_MAIN.getUrl();
    }

    public static String getDefaultHost(){
        return BaseHostConfig.WEPAY_HOST_MAIN.getUrl();
    }
}
