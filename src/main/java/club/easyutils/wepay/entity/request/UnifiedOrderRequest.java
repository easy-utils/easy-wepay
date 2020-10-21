package club.easyutils.wepay.entity.request;

import club.easyutils.wepay.adapter.xml.CdataJaxbAdapter;
import club.easyutils.wepay.entity.BaseRequest;
import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="xml")
public class UnifiedOrderRequest implements BaseRequest {

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
     * 设备号
     * 自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
     */
    @XmlElement(name = "device_info")
    private String deviceInfo;

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

    /**
     * 商品描述
     * 商品简单描述，该字段请按照规范传递，具体请见参数规定
     */
    @XmlElement(name = "body")
    @NonNull
    private String body;

    /**
     * 商品详情
     * 商品详细描述，对于使用单品优惠的商户，该字段必须按照规范上传，详见单品优惠参数说明
     * https://pay.weixin.qq.com/wiki/doc/api/danpin.php?chapter=9_102&index=2
     */
    @XmlElement(name = "detail")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String detail;

    /**
     * 附加数据
     * 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用。
     */
    @XmlElement(name = "attach")
    private String attach;

    /**
     * 商户订单号
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|* 且在同一个商户号下唯一。详见商户订单号
     * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_2
     */
    @XmlElement(name = "out_trade_no")
    @NonNull
    private String outTradeNo;

    /**
     * 标价币种
     * 符合ISO 4217标准的三位字母代码，默认人民币：CNY，详细列表请参见货币类型
     * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_2
     */
    @XmlElement(name = "fee_type")
    private String feeType;

    /**
     * 标价金额
     * 订单总金额，单位为分，详见支付金额
     * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_2
     */
    @XmlElement(name = "total_fee")
    @NonNull
    private String totalFee;

    /**
     * 终端IP
     * 支持IPV4和IPV6两种格式的IP地址。用户的客户端IP
     */
    @XmlElement(name = "spbill_create_ip")
    @NonNull
    private String spBillCreateIp;

    /**
     * 交易起始时间
     * 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
     * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_2
     */
    @XmlElement(name = "time_start")
    private String timeStart;

    /**
     * 交易结束时间
     * 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。
     * 订单失效时间是针对订单号而言的，由于在请求支付的时候有一个必传参数prepay_id只有两小时的有效期，所以在重入时间超过2小时的时候需要重新请求下单接口获取新的prepay_id。其他详见时间规则
     * time_expire只能第一次下单传值，不允许二次修改，二次修改系统将报错。如用户支付失败后，需再次支付，需更换原订单号重新下单。
     * 建议：最短失效时间间隔大于1分钟
     * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_2
     */
    @XmlElement(name = "time_expire")
    private String timeExpire;

    /**
     * 订单优惠标记
     * 订单优惠标记，使用代金券或立减优惠功能时需要的参数，说明详见代金券或立减优惠
     * https://pay.weixin.qq.com/wiki/doc/api/tools/sp_coupon.php?chapter=12_7&index=3
     */
    @XmlElement(name = "goods_tag")
    private String goodsTag;

    /**
     * 通知地址
     * 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
     */
    @XmlElement(name = "notify_url")
    @NonNull
    private String notifyUrl;

    /**
     * 交易类型
     * JSAPI-JSAPI支付、NATIVE-Native支付、APP-APP支付，说明详见参数规定
     * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_2
     */
    @XmlElement(name = "trade_type")
    @NonNull
    private String tradeType;

    /**
     * 商品ID
     * trade_type=NATIVE时，此参数必传。此参数为二维码中包含的商品ID，商户自行定义。
     */
    @XmlElement(name = "product_id")
    private String productId;

    /**
     * 指定支付方式
     * 上传此参数no_credit--可限制用户不能使用信用卡支付
     * https://pay.weixin.qq.com/wiki/doc/api/tools/sp_coupon.php?chapter=12_7&index=3
     */
    @XmlElement(name = "limit_pay")
    private String limitPay;

    /**
     * 用户标识
     * trade_type=JSAPI时（即JSAPI支付），此参数必传，此参数为微信用户在商户对应appid下的唯一标识。openid如何获取，可参考获取openid。
     * 企业号请使用企业号OAuth2.0接口获取企业号内成员userid，再调用企业号userid转openid接口进行转换
     * 【获取 OpenId】https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_4
     * 【企业号OAuth2.0接口】http://qydev.weixin.qq.com/wiki/index.php?title=OAuth验证接口
     * 【企业号userid转openid接口】http://qydev.weixin.qq.com/wiki/index.php?title=Userid与openid互换接口
     */
    @XmlElement(name = "openid")
    private String openId;

    /**
     * 电子发票入口开放标识
     * Y，传入Y时，支付成功消息和支付详情页将出现开票入口。需要在微信支付商户平台或微信公众平台开通电子发票功能，传此字段才可生效
     */
    @XmlElement(name = "receipt")
    private String receipt;

    /**
     * 场景信息
     * 该字段常用于线下活动时的场景信息上报，支持上报实际门店信息，商户也可以按需求自己上报相关信息。
     * 该字段为JSON对象数据，对象格式为{"store_info":{"id": "门店ID","name": "名称","area_code": "编码","address": "地址" }}
     *
     * - id 门店编号，由商户自定义
     * - name 门店名称 ，由商户自定义
     * - area_code 门店所在地行政区划码，详细见《最新县及县以上行政区划代码》，https://pay.weixin.qq.com/wiki/doc/api/download/store_adress.csv
     * - address 门店详细地址 ，由商户自定义
     */
    @XmlElement(name = "scene_info")
    private String sceneInfo;





}
