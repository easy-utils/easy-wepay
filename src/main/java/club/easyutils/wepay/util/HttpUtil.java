package club.easyutils.wepay.util;

import club.easyutils.wepay.entity.BaseRequest;
import club.easyutils.wepay.entity.BaseResponse;
import cn.hutool.core.lang.Validator;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.client.RestTemplate;

public class HttpUtil {

    private static RestTemplate restTemplate;

    public static RestTemplate getRestTemplate() {
        if (Validator.isNull(restTemplate)){
            restTemplate = new RestTemplate();
        }
        return restTemplate;
    }

    public static BaseResponse doPost(String url, BaseRequest baseRequest){
        return getRestTemplate().postForEntity(url, JSONObject.toJSONString(baseRequest), BaseResponse.class).getBody();
    }

    public static BaseResponse doGet(String url){
        return getRestTemplate().getForEntity(url, BaseResponse.class).getBody();
    }
}
