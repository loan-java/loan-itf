package com.mod.loan.itf.sms.moxintong;

public enum MoXinTongSmsSign {

    /**
     * 账号配置
     */
    HUA_SHI_DAI("huashidai", "00", "真好花", "httz001", "4dae6489da13cc5285058c9124346ef0"),
    MO_XIN_TONG("jishidai", "00", "真好花", "httz001", "4dae6489da13cc5285058c9124346ef0"),
    ZHEN_HAO_HUA("zhenhaohua", "00", "真好花", "httz001", "4dae6489da13cc5285058c9124346ef0"),
    ;

    private String key;

    private String signId;

    private String signName;

    /**
     * account
     */
    private String accesskey;

    /**
     * pwd
     */
    private String secret;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSignId() {
        return signId;
    }

    public void setSignId(String signId) {
        this.signId = signId;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getAccesskey() {
        return accesskey;
    }

    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    MoXinTongSmsSign(String key, String signId, String signName, String accesskey, String secret) {
        this.key = key;
        this.signId = signId;
        this.signName = signName;
        this.accesskey = accesskey;
        this.secret = secret;
    }

    public static MoXinTongSmsSign getSign(String key) {
        for (MoXinTongSmsSign smsSign : MoXinTongSmsSign.values()) {
            if (smsSign.getKey().equals(key)) {
                return smsSign;
            }
        }
        return null;
    }
}
