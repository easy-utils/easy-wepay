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
import java.math.BigDecimal;

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
     * 设备号
     * 自定义参数，可以为请求支付的终端设备号等
     */
    @XmlElement(name = "device_info")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String deviceInfo;

    /**
     * 用户标识
     * 用户在商户appid下的唯一标识
     */
    @XmlElement(name = "openid")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String openid;

    /**
     * 用户是否关注公众账号，Y-关注，N-未关注
     */
    @XmlElement(name = "is_subscribe")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String subscribe;

    /**
     * 交易类型
     * JSAPI-JSAPI支付、NATIVE-Native支付、APP-APP支付，说明详见参数规定
     * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_2
     */
    @XmlElement(name = "trade_type")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String tradeType;

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

    /**
     * 银行类型，采用字符串类型的银行标识
     */
    @XmlElement(name = "bank_type")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String bankType;

    /**
     * 订单总金额，单位为分
     */
    @XmlElement(name = "total_fee")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private BigDecimal totalFee;

    /**
     * 当订单使用了免充值型优惠券后返回该参数，应结订单金额=订单金额-免充值优惠券金额。
     */
    @XmlElement(name = "settlement_total_fee")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String settlementTotalFee;

    /**
     * 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2
     */
    @XmlElement(name = "fee_type")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String feeType;

    /**
     * 现金支付金额订单现金支付金额，详见支付金额
     * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2
     */
    @XmlElement(name = "cash_fee")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String cashFee;

    /**
     * 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2
     */
    @XmlElement(name = "cash_fee_type")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String cashFeeType;

    /**
     * “代金券”金额<=订单金额，订单金额-“代金券”金额=现金支付金额，详见支付金额
     * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2
     */
    @XmlElement(name = "coupon_fee")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String couponFee;

    /**
     * 代金券使用数量
     */
    @XmlElement(name = "coupon_count")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String couponCount;

    /**
     * 微信支付订单号
     */
    @XmlElement(name = "transaction_id")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String transactionId;

    /**
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     */
    @XmlElement(name = "out_trade_no")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String outTradeNo;

    /**
     * 附加数据，原样返回
     */
    @XmlElement(name = "attach")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String attach;

    /**
     * 订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
     * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2
     */
    @XmlElement(name = "time_end")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String timeEnd;

    /**
     * 对当前查询订单状态的描述和下一步操作的指引
     */
    @XmlElement(name = "trade_state_desc")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String tradeStateDesc;

    // ----- ----- Getter with XmlCDATA Tag ----- -----

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
    public String getDeviceInfo() {
        return deviceInfo;
    }

    @XmlCDATA
    public String getOpenid() {
        return openid;
    }

    @XmlCDATA
    public String getTradeType() {
        return tradeType;
    }

    @XmlCDATA
    public String getSubscribe() {
        return subscribe;
    }

    @XmlCDATA
    public String getTradeState() {
        return tradeState;
    }

    @XmlCDATA
    public String getBankType() {
        return bankType;
    }

    @XmlCDATA
    public BigDecimal getTotalFee() {
        return totalFee;
    }

    @XmlCDATA
    public String getSettlementTotalFee() {
        return settlementTotalFee;
    }

    @XmlCDATA
    public String getFeeType() {
        return feeType;
    }

    @XmlCDATA
    public String getCashFee() {
        return cashFee;
    }

    @XmlCDATA
    public String getCashFeeType() {
        return cashFeeType;
    }

    @XmlCDATA
    public String getCouponFee() {
        return couponFee;
    }

    @XmlCDATA
    public String getCouponCount() {
        return couponCount;
    }

    @XmlCDATA
    public String getTransactionId() {
        return transactionId;
    }

    @XmlCDATA
    public String getOutTradeNo() {
        return outTradeNo;
    }

    @XmlCDATA
    public String getAttach() {
        return attach;
    }

    @XmlCDATA
    public String getTimeEnd() {
        return timeEnd;
    }

    @XmlCDATA
    public String getTradeStateDesc() {
        return tradeStateDesc;
    }
}
