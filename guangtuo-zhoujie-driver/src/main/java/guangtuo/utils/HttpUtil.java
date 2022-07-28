package guangtuo.utils;

import com.tuya.could.tedge.sdk.common.model.ProtocolProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @author luamng.wei@tuya.com
 * @date 2021/12/23
 */
@Slf4j
public class HttpUtil {
    public String connect(Map<String, ProtocolProperties> map,String urlPath){
        ProtocolProperties httpInfo = map.get("http");
        if (ObjectUtils.isEmpty(httpInfo)){
            log.error("cannot found ProtocolProperties for `http`");
            return "false";
        }
        Map<String, String> customMap = httpInfo.getCustomMap();
        String host = customMap.get("host");
        if (host.isEmpty()){
            log.error("missing host");
            return "false";
        }
        String port = customMap.get("port");
        if (port.isEmpty()){
            log.error("missing port");
            return "false";
        }
        // TODO: 2021/12/23 建立连接并返回
        HttpClient httpClient = new HttpClient();
        // 设置httpClient连接主机服务器超时时间：15000毫秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        // 创建post请求方法实例对象
        GetMethod getMethod = new GetMethod (urlPath);
        String response = "";
        try {
           response = getMethod.getResponseBodyAsString();

        }catch (IOException exception){

        }
    return response;

    }



}
