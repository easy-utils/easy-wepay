package club.easyutils.wepay.entity.request;

import club.easyutils.wepay.entity.BaseRequest;
import lombok.*;
import org.eclipse.persistence.oxm.annotations.XmlCDATA;

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
public class OrderQueryRequest extends BaseRequest {

    // ---------- Request Attributes ----------

    /**
     * 公众账号ID
     * 微信支付分配的公众账号ID（企业号corpid即为此appId）
     */
    @XmlElement(name="appid")
    @NonNull
    private String appid;

    /**
     * 商户号
     * 微信支付分配的商户号
     */
    @XmlElement(name="mch_id")
    @NonNull
    private String mch_id;

    /**
     * 微信订单号
     * 微信的订单号，建议优先使用。和商户订单号二选一
     */
    @XmlElement(name = "transaction_id")
    private String transaction_id;

    /**
     * 商户订单号
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一，和微信订单号二选一。详见商户订单号
     * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_2
     */
    @XmlElement(name = "out_trade_no")
    private String out_trade_no;


    /**
     * 随机字符串
     * 随机字符串，长度要求在32位以内。推荐随机数生成算法
     * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_3
     */
    @XmlElement(name = "nonce_str")
    @NonNull
    private String nonce_str;

    /**
     * 签名
     * 通过签名算法计算得出的签名值，详见签名生成算法
     * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_3
     */
    @XmlElement(name = "sign")
    private String sign;

    /**
     * 签名类型
     * 签名类型，默认为MD5，支持HMAC-SHA256和MD5。
     */
    @XmlElement(name = "sign_type")
    private String sign_type;

    @XmlCDATA
    public String getAppid() {
        return appid;
    }

    @XmlCDATA
    public String getMch_id() {
        return mch_id;
    }

    @XmlCDATA
    public String getTransaction_id() {
        return transaction_id;
    }

    @XmlCDATA
    public String getOut_trade_no() {
        return out_trade_no;
    }

    @XmlCDATA
    public String getNonce_str() {
        return nonce_str;
    }

    @XmlCDATA
    public String getSign() {
        return sign;
    }

    @XmlCDATA
    public String getSign_type() {
        return sign_type;
    }
}
