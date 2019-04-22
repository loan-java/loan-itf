package com.mod.loan.itf.sms.moxintong;

/**
 * @author kk
 */
public enum MoXinTongSmsTemplate {
    /**
     * 模板
     */
    T2003("2003", "27423", "您的订单明日结束，打开app-我的订单，实时查看详情，感谢您对我们的支持，祝您生活愉快！回T退订"),

    ;

    /**
     * 自有模板key
     */
    private String key;

    /**
     * 第三方模板key
     */
    private String templateId;

    /**
     * 模板内容
     */
    private String content;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    MoXinTongSmsTemplate(String key, String templateId, String content) {
        this.key = key;
        this.templateId = templateId;
        this.content = content;
    }

    public static MoXinTongSmsTemplate getTemplate(String key) {
        for (MoXinTongSmsTemplate template : MoXinTongSmsTemplate.values()) {
            if (template.getKey().equals(key)) {
                return template;
            }
        }
        return null;
    }
}
