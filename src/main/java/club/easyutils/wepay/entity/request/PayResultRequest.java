package club.easyutils.wepay.entity.request;

import club.easyutils.wepay.adapter.xml.CdataJaxbAdapter;
import club.easyutils.wepay.entity.BaseRequest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * 支付结果通知
 * @author rainyblossom
 */
public class PayResultRequest extends BaseRequest {

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

    /**
     * 用户标识
     * 用户在商户appid下的唯一标识
     */
    @XmlElement(name = "openid")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String openid;

    /**
     * 用户是否关注公众账号
     * Y-关注，N-未关注
     */
    @XmlElement(name = "is_subscribe")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String isSubscribe;


    /**
     * 交易类型
     * JSAPI、NATIVE、APP
     */
    @XmlElement(name = "trade_type")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String tradeType;

    /**
     * 付款银行
     * 银行类型，采用字符串类型的银行标识，银行类型见银行列表
     * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_2
     */
    @XmlElement(name = "bank_type")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String bankType;

    /**
     * 订单金额
     * 订单总金额，单位为分
     */
    @XmlElement(name = "total_fee")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String totalFee;


    /**
     * 交易类型
     * JSAPI、NATIVE、APP
     */
    @XmlElement(name = "settlement_total_fee")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String settlementTotalFee;

    /**
     * 应结订单金额
     * 应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
     */
    @XmlElement(name = "fee_type")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String feeType;

    /**
     * 现金支付金额
     * 现金支付金额订单现金支付金额，详见支付金额
     * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_2
     */
    @XmlElement(name = "cash_fee")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String cashFee;


    /**
     * 现金支付货币类型
     * 货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_2
     */
    @XmlElement(name = "cash_fee_type")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String cashFeeType;

    /**
     * 总代金券金额
     * 代金券金额<=订单金额，订单金额-代金券金额=现金支付金额，详见支付金额
     * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_2
     */
    @XmlElement(name = "coupon_fee")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String couponFee;

    /**
     * 代金券使用数量
     * 代金券使用数量
     */
    @XmlElement(name = "coupon_count")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String couponCount;


    /**
     * 代金券类型
     * CASH--充值代金券
     * NO_CASH---非充值代金券
     * 并且订单使用了免充值券后有返回（取值：CASH、NO_CASH）。$n为下标,从0开始编号，举例：coupon_type_0
     * 注意：只有下单时订单使用了优惠，回调通知才会返回券信息。
     * 下列情况可能导致订单不可以享受优惠：可能情况。
     * https://pay.weixin.qq.com/wiki/doc/api/danpin.php?chapter=9_202&index=7#menu4
     */
    @XmlElement(name = "coupon_type")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String couponType;

    /**
     * 代金券ID
     * 代金券ID,$n为下标，从0开始编号
     * 注意：只有下单时订单使用了优惠，回调通知才会返回券信息。
     * 下列情况可能导致订单不可以享受优惠：可能情况。
     * https://pay.weixin.qq.com/wiki/doc/api/danpin.php?chapter=9_202&index=7#menu4
     */
    @XmlElement(name = "coupon_id")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String couponId;

    /**
     * 微信支付订单号
     */
    @XmlElement(name = "transaction_id")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String transactionId;

    /**
     * 商户订单号
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     */
    @XmlElement(name = "out_trade_no")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String outTradeNo;

    /**
     * 商家数据包
     * 商家数据包，原样返回
     */
    @XmlElement(name = "attach")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String attach;

    /**
     * 支付完成时间
     * 支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
     * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_2
     */
    @XmlElement(name = "time_end")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String timeEnd;


}
