//package com.mod.loan.itf.voice.tencent;
//
//public enum EnumTencentTemplate {
//	V1001("10001","127436","尊敬的客户%s您好！您在%s上的订单时间已到期，请登录app及时查看"),
//
//	;
//	private String key;  //自有模板key
//
//    private String templateId;//第三方模板key
//
//    private String content; //模板内容
//
//	public String getKey() {
//		return key;
//	}
//
//	public void setKey(String key) {
//		this.key = key;
//	}
//
//	public String getTemplateId() {
//		return templateId;
//	}
//
//	public void setTemplateId(String templateId) {
//		this.templateId = templateId;
//	}
//
//	public String getContent() {
//		return content;
//	}
//
//	public void setContent(String content) {
//		this.content = content;
//	}
//
//
//	private EnumTencentTemplate(String key, String templateId, String content) {
//		this.key = key;
//		this.templateId = templateId;
//		this.content = content;
//	}
//
//	public static EnumTencentTemplate getTemplate(String key) {
//        for (EnumTencentTemplate enumYunpianApikey : EnumTencentTemplate.values()) {
//        	   if (enumYunpianApikey.getKey().equals(key)) {
//        		   return enumYunpianApikey;
//			   }
//        }
//        return null;
//    }
//}
