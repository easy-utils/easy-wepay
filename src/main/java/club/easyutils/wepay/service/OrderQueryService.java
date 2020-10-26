package club.easyutils.wepay.service;

import org.springframework.stereotype.Service;

/**
 * 该接口提供所有微信支付订单的查询，商户可以通过查询订单接口主动查询订单状态，完成下一步的业务逻辑。
 * 需要调用查询接口的情况：
 * ◆ 当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知；
 * ◆ 调用支付接口后，返回系统错误或未知交易状态情况；
 * ◆ 调用付款码支付API，返回USERPAYING的状态；
 * ◆ 调用关单或撤销接口API之前，需确认支付状态；
 */
@Service
public class OrderQueryService {

//    query(){
//
//    }



}
