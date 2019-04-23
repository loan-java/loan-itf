//package com.mod.loan.itf.voice.lianxin;
//
//public enum LianXinEnum {
//	DEV("dev", "C2019021518374984984", "8d1396b4a30fef8c9cdedc8077e097dc", "VT2019021518480232186"),
//	ONLINE("online", "C2019021518374984984", "8d1396b4a30fef8c9cdedc8077e097dc", "VT2019021518480232186"),
//	SUIXINGHUA("suixinghua", "C2019021516550934889", "a00195cc325a44530cc0b8c627a3f5dd", "VT2019021517340238030"),
//	JINGYU("jingyu", "C2019022110245618315", "b45f98745c909a599a3570014955f576", "VT2019022110534914455"),
//	MOFU("mofu", "C2019030518121965896", "eb2c13d55ccff27cf7f22f2847bdb635", "VT2019030518162026665");
//
//	private String projectAlias;
//	private String cusNo;
//	private String key;
//	private String templateId;
//
//	LianXinEnum(String projectAlias, String cusNo, String key,String templateId) {
//		this.projectAlias = projectAlias;
//		this.cusNo = cusNo;
//		this.key = key;
//		this.templateId = templateId;
//	}
//
//	public String getProjectAlias() {
//		return projectAlias;
//	}
//
//	public void setProjectAlias(String projectAlias) {
//		this.projectAlias = projectAlias;
//	}
//
//	public String getCusNo() {
//		return cusNo;
//	}
//
//	public void setCusNo(String cusNo) {
//		this.cusNo = cusNo;
//	}
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
//	public static LianXinEnum getLianXinParam(String projectAlias) {
//		for (LianXinEnum lianXinEnum : LianXinEnum.values()) {
//			if (lianXinEnum.getProjectAlias().equals(projectAlias)) {
//				return lianXinEnum;
//			}
//		}
//		return null;
//	}
//
//}
