package guangtuo;

import com.alibaba.fastjson.JSON;
import com.tuya.could.tedge.sdk.DpModelGatewayCallback;
import com.tuya.could.tedge.sdk.common.model.*;
import com.tuya.could.tedge.sdk.domain.service.iface.DpModelApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lumang.wei@tuya.com
 * @since 2021/12/22 16:00
 */
@Slf4j
public class DpDriverImpl implements DpModelGatewayCallback {
    private DpModelApi api;
    private DeviceApi deviceApi;
    private ChannelApi channelApi;
    /**
     * 设备添加，设备删除，设备更新回调接口
     * @param deviceNotifyType
     * @param deviceInfo
     */
    @Override
    public void deviceNotify(DeviceNotifyType deviceNotifyType, DeviceInfo deviceInfo) {

    }

    /**
     * 产品添加，产品更新，产品删除回调接口
     * @param productNotifyType
     * @param productInfo
     */
    @Override
    public void productNotify(ProductNotifyType productNotifyType, ProductInfo productInfo) {

    }

    /**
     * 网关关闭回调接口
     */
    @Override
    public void stop() {

    }

    /**
     * 下发指令接口
     * @param cid 自设备的cid
     * @param commandRequest
     * @param map
     * @param map1
     */
    @Override
    public void handleCommands(String cid, CommandRequest commandRequest, Map<String, ProtocolProperties> map, Map<String, DpExtend> map1) {
        log.info("COMMAND => cid：{},handleCommands:{}", cid, commandRequest);
        log.info("COMMAND => devProperty:{}" + map.toString());
        log.info("COMMAND => dpProperty:{}" + map1.toString());
        //获取下发指令中的dpmap
        Object dps = commandRequest.getData().get("dps");
        if (ObjectUtils.isEmpty(dps)){
            log.error("cannot found dps,cid:{}", cid);
            return;
        }
        Map<String, String> dpMap = new HashMap<>();
        try {
           dpMap = JSON.parseObject(dps.toString(), Map.class);
        }catch (Exception e){
            log.error("illegal  dps,cid:{}", cid);
            return;
        }
        DeviceInfo deviceInfoById = api.getDeviceInfoById(cid);
        if (ObjectUtils.isEmpty(deviceInfoById)){
            log.error("device info error,cid:{}", cid);
            return;
        }
        String productId = deviceInfoById.getProductId();
        if (StringUtils.isBlank(productId)){
            log.error("product info error,cid:{}",cid);
            return;
        }
        // TODO: 2021/12/23  判断产品类型
        if (productId.equals("")){
            deviceApi.handle(cid,dpMap,map,map1);
        }else if (){
            channelApi.handle(cid,dpMap,map,map1);
        }else {
            log.error("product id error,cid:{}",cid);
            return;
        }

    }
}
