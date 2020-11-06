package club.easyutils.wepay.service;

import club.easyutils.wepay.config.UnifiedOrderConfig;
import club.easyutils.wepay.entity.request.UnifiedOrderRequest;
import club.easyutils.wepay.entity.response.UnifiedOrderResponse;
import club.easyutils.wepay.util.HttpUtil;
import club.easyutils.wepay.util.XmlUtil;
import org.springframework.stereotype.Service;

/**
 * 统一下单接口
 *
 * 除付款码支付场景以外，商户系统先调用该接口在微信支付服务后台生成预支付交易单，
 * 返回正确的预支付交易会话标识后再按Native、JSAPI、APP等不同场景生成交易串调起支付。
 * 文档地址 https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1
 *
 * @author rainyblossom
 */
@Service
public class UnifiedOrderService {

    /**
     * 创建统一支付预订单
     * @param unifiedOrderRequest
     * @return
     */
    public UnifiedOrderResponse create(UnifiedOrderRequest unifiedOrderRequest){

        String xmlStr = HttpUtil.doPost(UnifiedOrderConfig.WEPAY_ORDER_UNIFIED.getUrl(), unifiedOrderRequest);
        return XmlUtil.xmlToBean(UnifiedOrderResponse.class, xmlStr);
    }

}
