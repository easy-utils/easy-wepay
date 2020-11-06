package club.easyutils.wepay.entity.response;

import club.easyutils.wepay.adapter.xml.CdataJaxbAdapter;
import club.easyutils.wepay.entity.BaseRequest;
import lombok.Builder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * 支付结果通知
 * @author rainyblossom
 */
@Builder
public class PayResultResponse extends BaseRequest {

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

}
