package guangtuo.emuns;

/**
 * @author lumamg.wei@tuya.com
 * @since 2021/12/23 16:51
 */
public enum ChannelEnum {
    private String dpId;
    private String url;
    private String desc;

    ChannelEnum(String dpId, String url, String desc){
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
