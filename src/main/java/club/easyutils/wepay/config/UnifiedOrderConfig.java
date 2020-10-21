package club.easyutils.wepay.config;

import club.easyutils.wepay.util.HostUtil;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UnifiedOrderConfig {

    /** 统一下单接口 **/
    WEPAY_ORDER_UNIFIED("/pay/unifiedorder");


    private String url;

    public String getUrl() {
        return HostUtil.getDoubleLiveHost() + url;
    }
}
