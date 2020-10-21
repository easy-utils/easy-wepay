package club.easyutils.wepay.entity.request;

import club.easyutils.wepay.adapter.xml.CdataJaxbAdapter;
import club.easyutils.wepay.entity.BaseResponse;
import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="xml")
public class UnifiedOrderResponse implements BaseResponse {

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
     * 设备号
     * 自定义参数，可以为请求支付的终端设备号等
     */
    @XmlElement(name = "device_info")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String deviceInfo;

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
     * 交易类型
     * JSAPI-JSAPI支付、NATIVE-Native支付、APP-APP支付，说明详见参数规定
     * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_2
     */
    @XmlElement(name = "err_code_des")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String tradeType;

    /**
     * 预支付交易会话标识
     * 微信生成的预支付会话标识，用于后续接口调用中使用，该值有效期为2小时
     */
    @XmlElement(name = "prepay_id")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String prepayId;

    /**
     * 二维码链接
     * trade_type=NATIVE时有返回，此url用于生成支付二维码，然后提供给用户进行扫码支付。
     * 注意：code_url的值并非固定，使用时按照URL格式转成二维码即可
     */
    @XmlElement(name = "code_url")
    @XmlJavaTypeAdapter(value= CdataJaxbAdapter.class)
    private String codeUrl;


}
