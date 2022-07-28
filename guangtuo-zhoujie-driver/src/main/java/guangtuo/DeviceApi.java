package guangtuo;

import com.tuya.could.tedge.sdk.common.model.DpExtend;
import com.tuya.could.tedge.sdk.common.model.NonDpData;
import com.tuya.could.tedge.sdk.common.model.ProtocolProperties;
import com.tuya.could.tedge.sdk.domain.service.iface.DpModelApi;
import guangtuo.emuns.DeviceEnum;
import guangtuo.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
public class DeviceApi {
    private DpModelApi api;
    private HttpUtil httpUtil;

    public DeviceApi(DpModelApi api, HttpUtil httpUtil) {
        this.api = api;
        this.httpUtil = httpUtil;
    }

    public void handle(String cid, Map<String, String> dpMap, Map<String, ProtocolProperties> map, Map<String, DpExtend> map1) {
        for (String dpId : dpMap.keySet()) {
           if (dpId.equals(DeviceEnum.DEVICE_LIST.getDpId())){
               deviceInfo();
           }
        }
    }


    //获取设备信息
    private void deviceInfo(){
        String urlPath= DeviceEnum.DEVICE_LIST.getUrl();
        String response = httpUtil.connect(Collections.emptyMap(), urlPath);
        // TODO: 2021/12/23

        List<Map<String,String>> deviceInfo = new ArrayList<>();
        api.reportNonDpData(new NonDpData(7,System.currentTimeMillis(),System.currentTimeMillis(),deviceInfo));

    }



}
