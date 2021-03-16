package club.easyutils.wepay.entity.response;

import club.easyutils.wepay.adapter.xml.CdataJaxbAdapter;
import club.easyutils.wepay.entity.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.persistence.oxm.annotations.XmlCDATA;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * 订单查询响应实体类
 * @author rainyblossom
 */
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderQueryResponse extends BaseResponse {

    // ---------- Response Attributes ----------

    /**
     * 返回状态码
     * SUCCESS/FAIL
     * 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
     */
    @XmlElement(name="return_code")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String returnCode;

    /**
     * 返回信息
     * 当return_code为FAIL时返回信息为错误原因，例如：签名失败、参数格式校验错误
     */
    @XmlElement(name="return_msg")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String returnMsg;

    // ---------- 以下字段在return_code为SUCCESS的时候有返回 ----------

    /**
     * 公众账号ID
     * 调用接口提交的公众账号ID
     */
    @XmlElement(name = "appid")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String appId;

    /**
     * 商户号
     * 调用接口提交的商户号
     */
    @XmlElement(name="mch_id")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String mchId;

    /**
     * 随机字符串
     * 微信返回的随机字符串
     */
    @XmlElement(name = "nonce_str")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String nonceStr;

    /**
     * 签名
     * 微信返回的签名值，详见签名算法
     * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_3
     */
    @XmlElement(name = "sign")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String sign;

    /**
     * 业务结果
     * SUCCESS/FAIL
     */
    @XmlElement(name = "result_code")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String resultCode;

    /**
     * 错误代码
     * 当result_code为FAIL时返回错误代码，详细参见下文错误列表
     * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1
     */
    @XmlElement(name = "err_code")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String errCode;

    /**
     * 错误代码描述
     * 当result_code为FAIL时返回错误描述，详细参见下文错误列表
     * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1
     */
    @XmlElement(name = "err_code_des")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String errCodeDes;

    // ---------- 以下字段在return_code 、result_code、trade_state都为SUCCESS时有返回 ，如trade_state不为 SUCCESS，则只返回out_trade_no（必传）和attach（选传）。 ----------

    /**
     * SUCCESS--支付成功
     * REFUND--转入退款
     * NOTPAY--未支付
     * CLOSED--已关闭
     * REVOKED--已撤销(刷卡支付)
     * USERPAYING--用户支付中
     * PAYERROR--支付失败(其他原因，如银行返回失败)
     * ACCEPT--已接收，等待扣款
     * 支付状态机请见下单API页面
     */
    @XmlElement(name = "trade_state")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String tradeState;

    @XmlCDATA
    public String getReturnCode() {
        return returnCode;
    }

    @XmlCDATA
    public String getReturnMsg() {
        return returnMsg;
    }

    @XmlCDATA
    public String getAppId() {
        return appId;
    }

    @XmlCDATA
    public String getMchId() {
        return mchId;
    }

    @XmlCDATA
    public String getNonceStr() {
        return nonceStr;
    }

    @XmlCDATA
    public String getSign() {
        return sign;
    }

    @XmlCDATA
    public String getResultCode() {
        return resultCode;
    }

    @XmlCDATA
    public String getErrCode() {
        return errCode;
    }

    @XmlCDATA
    public String getErrCodeDes() {
        return errCodeDes;
    }

    @XmlCDATA
    public String getTradeState() {
        return tradeState;
    }
}
