package guangtuo.emuns;

/**
 * @author luamng.wei@tuya.com
 * @date 2021/12/23 16:30
 */
public enum DeviceEnum {

    DEVICE_LIST("5","/device/list","获取全部设备信息");
    private String dpId;
    private String url;
    private String desc;

     DeviceEnum(String dpId, String url, String desc){
        this.desc = desc;
        this.dpId = dpId;
        this.url = url;
    }

    public String getDpId() {
        return dpId;
    }

    public String getUrl() {
        return url;
    }

    public String getDesc() {
        return desc;
    }
}
