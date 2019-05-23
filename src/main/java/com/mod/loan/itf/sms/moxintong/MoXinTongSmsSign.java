package com.mod.loan.itf.sms.moxintong;

public enum MoXinTongSmsSign {

    /**
     * 账号配置
     */
    //普通短信
    T002("002", "00", "普通短信", "httz002", "4dae6489da13cc5285058c9124346ef0"),
    //催收短信
    T005("005", "00", "催收短信", "httz005", "4dae6489da13cc5285058c9124346ef0"),
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
