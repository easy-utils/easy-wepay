package club.easyutils.wepay.service;

import club.easyutils.wepay.entity.request.PayResultRequest;
import club.easyutils.wepay.entity.response.PayResultResponse;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 支付完成后，微信会把相关支付结果及用户信息通过数据流的形式发送给商户，商户需要接收处理，并按文档规范返回应答。
 * 注意：
 * 1、同样的通知可能会多次发送给商户系统。商户系统必须能够正确处理重复的通知。
 * 2、后台通知交互时，如果微信收到商户的应答不符合规范或超时，微信会判定本次通知失败，重新发送通知，直到成功为止（在通知一直不成功的情况下，微信总共会发起多次通知，通知频率为15s/15s/30s/3m/10m/20m/30m/30m/30m/60m/3h/3h/3h/6h/6h - 总计 24h4m），但微信不保证通知最终一定能成功。
 * 3、在订单状态不明或者没有收到微信支付结果通知的情况下，建议商户主动调用微信支付【查询订单API】确认订单状态。
 * 特别提醒：
 * 1、商户系统对于支付结果通知的内容一定要做签名验证,并校验返回的订单金额是否与商户侧的订单金额一致，防止数据泄漏导致出现“假通知”，造成资金损失。
 * 2、当收到通知进行处理时，首先检查对应业务数据的状态，判断该通知是否已经处理过，如果没有处理过再进行处理，如果处理过直接返回结果成功。在对业务数据进行状态检查和处理之前，要采用数据锁进行并发控制，以避免函数重入造成的数据混乱。
 * 3、技术人员可登进微信商户后台扫描加入接口报警群，获取接口告警信息。
 *
 * 该链接是通过【统一下单API】中提交的参数notify_url设置，如果链接无法访问，商户将无法接收到微信通知。
 * 通知url必须为直接可访问的url，不能携带参数。示例：notify_url：“https://pay.weixin.qq.com/wxpay/pay.action”
 *
 * @author rainyblossom
 */
public class OrderResultService {

    public PayResultResponse notify(@RequestBody PayResultRequest resultRequest){
        return PayResultResponse.builder().build();
    }
}
