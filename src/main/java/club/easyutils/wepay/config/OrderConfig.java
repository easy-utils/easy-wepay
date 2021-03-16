package club.easyutils.wepay.config;

import club.easyutils.wepay.util.HostUtil;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderConfig {

    /** 统一下单接口 **/
    WEPAY_ORDER_UNIFIED("/pay/unifiedorder"),
    /** 统一下单接口 **/
    WEPAY_ORDER_CLOSE("/pay/closeorder"),
    /** 统一下单接口 **/
    WEPAY_ORDER_QUERY("/pay/orderquery")



    ;


    private String url;

    public String getUrl() {
        return HostUtil.getDoubleLiveHost() + url;
    }
}
