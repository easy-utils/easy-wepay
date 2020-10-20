package club.easyutils.wepay.config;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UnifiedOrderConfig {

    /** 统一下单接口 **/
    WEPAY_ORDER_UNIFIED("/pay/unifiedorder");


    private String url;

    public String getUrl() {
        return BaseHostConfig.WEPAY_HOST_MAIN.getUrl() + url;
    }
}
