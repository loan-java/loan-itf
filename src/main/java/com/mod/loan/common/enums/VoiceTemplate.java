package com.mod.loan.common.enums;

public enum VoiceTemplate {
	V10001("10001"),

	;
	private String key; // 自有模板key

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	VoiceTemplate(String key) {
		this.key = key;
	}

}
