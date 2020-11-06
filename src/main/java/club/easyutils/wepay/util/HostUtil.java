package club.easyutils.wepay.util;

import club.easyutils.wepay.config.BaseHostConfig;
import club.easyutils.wepay.config.HostSwitchConfig;
import club.easyutils.wepay.handler.doubleLive.WePayHostDoubleLiveHandler;
import cn.hutool.core.lang.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class HostUtil {

    @Autowired(required = false)
    private WePayHostDoubleLiveHandler doubleLiveHandler;

    @Autowired(required = false)
    private HostSwitchConfig hostSwitchConfig;

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
        if (Validator.isNotNull(hostUtil.hostSwitchConfig)){
            if (hostUtil.hostSwitchConfig.doubleLiveSwitch()){
                if (Validator.isNotNull(hostUtil.doubleLiveHandler)){
                    return hostUtil.doubleLiveHandler.getHost();
                }
            }
        }
        return BaseHostConfig.WEPAY_HOST_MAIN.getUrl();
    }

    public static String getDefaultHost(){
        return BaseHostConfig.WEPAY_HOST_MAIN.getUrl();
    }
}
