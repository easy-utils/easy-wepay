package club.easyutils.wepay.util;

import club.easyutils.wepay.converter.CustomHttpMessageConverter;
import club.easyutils.wepay.entity.BaseRequest;
import club.easyutils.wepay.entity.BaseResponse;
import cn.hutool.core.lang.Validator;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

public class HttpUtil {

    private static RestTemplate restTemplate;

    public static RestTemplate getRestTemplate() {
        if (Validator.isNull(restTemplate)){
            restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        }
        return restTemplate;
    }

    public static String doPost(String url, BaseRequest baseRequest){
        return getRestTemplate().postForEntity(url, XmlUtil.beanToXml(baseRequest, true), String.class).getBody();
    }

    public static BaseResponse doGet(String url){
        return getRestTemplate().getForEntity(url, BaseResponse.class).getBody();
    }
}
