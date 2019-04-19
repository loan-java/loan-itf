package com.mod.loan.itf.sms.miyun;

public enum MiYunSmsTemplate {
	T1001("1001","27003","您的验证码为%s，请于%s内正确输入，如非本人操作，请忽略此短信。"),//注册
	T1002("1002","27003","您的验证码为%s，请于%s内正确输入，如非本人操作，请忽略此短信。"),//忘记密码

	T2004("2004","31769","尊敬的用户：您的借款订单已成功打款%s元，请留意款项到账情况。回T退订"),//分期打款
	T2001("2001","27002","恭喜您下单成功，%s元已打至您的收款账户，请于%s前及时回购！实际到账时间以银行为准。回T退订"),//打款

	T2002("2002","27422","您的订单即将结束，打开app-我的订单，实时查看详情，感谢您对我们的支持，祝您生活愉快！回T退订"),//当天提醒
	T2003("2003","27423","您的订单明日结束，打开app-我的订单，实时查看详情，感谢您对我们的支持，祝您生活愉快！回T退订"),//提前提醒

	;
	private String key;  //自有模板key

    private String templateId;//第三方模板key

    private String content; //模板内容

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


	private MiYunSmsTemplate(String key, String templateId, String content) {
		this.key = key;
		this.templateId = templateId;
		this.content = content;
	}

	public static MiYunSmsTemplate getTemplate(String key) {
        for (MiYunSmsTemplate enumYunpianApikey : MiYunSmsTemplate.values()) {
        	   if (enumYunpianApikey.getKey().equals(key)) {
        		   return enumYunpianApikey;
			   }        
        }
        return null;
    }
}
