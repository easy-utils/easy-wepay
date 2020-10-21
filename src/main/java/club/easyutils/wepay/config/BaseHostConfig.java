package club.easyutils.wepay.config;

import club.easyutils.wepay.util.HostUtil;
import lombok.AllArgsConstructor;

/**
 * 微信支付 Base Host
 * 该方法会自动调用双活机制，如果不需要双活机制请使用 getBasicUrl() 方法
 * @author rainyblossom
 */
@AllArgsConstructor
public enum BaseHostConfig {

    WEPAY_HOST_MAIN("https://api.mch.weixin.qq.com"),

    WEPAY_HOST_STANDBY("https://api2.mch.weixin.qq.com");

    private String url;

    public String getUrl() {
        return url;
    }

}
