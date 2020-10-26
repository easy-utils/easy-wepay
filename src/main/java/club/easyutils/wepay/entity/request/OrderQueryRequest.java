package club.easyutils.wepay.entity.request;

import club.easyutils.wepay.entity.BaseRequest;
import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 订单查询请求实体类
 * @author rainyblossom
 */
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="xml")
public class OrderQueryRequest implements BaseRequest {

    // ---------- Request Attributes ----------

    /**
     * 公众账号ID
     * 微信支付分配的公众账号ID（企业号corpid即为此appId）
     */
    @XmlElement(name="appid")
    @NonNull
    private String appId;

    /**
     * 商户号
     * 微信支付分配的商户号
     */
    @XmlElement(name="mch_id")
    @NonNull
    private String mchId;

    /**
     * 微信订单号
     * 微信的订单号，建议优先使用。和商户订单号二选一
     */
    @XmlElement(name = "transaction_id")
    @NonNull
    private String transactionId;

    /**
     * 商户订单号
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一，和微信订单号二选一。详见商户订单号
     * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_2
     */
    @XmlElement(name = "out_trade_no")
    @NonNull
    private String outTradeNo;


    /**
     * 随机字符串
     * 随机字符串，长度要求在32位以内。推荐随机数生成算法
     * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_3
     */
    @XmlElement(name = "nonce_str")
    @NonNull
    private String nonceStr;

    /**
     * 签名
     * 通过签名算法计算得出的签名值，详见签名生成算法
     * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_3
     */
    @XmlElement(name = "sign")
    @NonNull
    private String sign;

    /**
     * 签名类型
     * 签名类型，默认为MD5，支持HMAC-SHA256和MD5。
     */
    @XmlElement(name = "sign_type")
    private String signType;
}
