package club.easyutils.wepay.util;

import club.easyutils.wepay.model.BaseRequestModel;
import club.easyutils.wepay.model.BaseResponseModel;
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

    public static BaseResponseModel doPost(String url, BaseRequestModel baseRequestModel){
        return getRestTemplate().postForEntity(url, JSONObject.toJSONString(baseRequestModel), BaseResponseModel.class).getBody();
    }

    public static BaseResponseModel doGet(String url){
        return getRestTemplate().getForEntity(url, BaseResponseModel.class).getBody();
    }
}
