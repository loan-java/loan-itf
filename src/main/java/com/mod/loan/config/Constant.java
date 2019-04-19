package com.mod.loan.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Constant {

	public static String ENVIROMENT;
	public static String PROJECT_ALIAS;

	public static String SERVER_API_URL;
	public static String SERVER_H5_URL;
	public static String SERVER_ITF_URL;

	public static String MOXIE_ACCESSKEY_ID;
	public static String MOXIE_ACCESS_KEY_SECRET;

	public static String MOXIE_CUSTOMER_ID;
	public static String MOXIE_MAGIC_SECRET;

	public static String MOXIE_ZM_SCORE;
	public static String MOXIE_MAGIC_TOKEN;
	public static String MOXIE_MAGIC_REPORT;
	public static String MOXIE_JXREPORT;
	public static String MOXIE_MXDATA_EX;
	public static String MOXIE_ALIPAY;

	public static String OSS_ENPOINT_OUT;
	public static String OSS_ENPOINT_IN;
	public static String OSS_MOXIE_BUCKET_NAME;
	public static String OSS_MOXIE_MOBILE_JXL;
	public static String OSS_MOXIE_MOBILE_MXDATA;
	public static String OSS_MOXIE_ZFB_DATA;
	public static String OSS_MOXIE_MAGIC_WAND;
	public static String OSS_MOXIE_ADDRESS_LIST;

	@Value("${environment:}")
	public void setENVIROMENT(String environment) {
		Constant.ENVIROMENT = environment;
	}
	
	@Value("${project.alias:}")
	public void setPROJECT_ALIAS(String pROJECT_ALIAS) {
		Constant.PROJECT_ALIAS = pROJECT_ALIAS;
	}

	@Value("${server.api.url:}")
	public void setServerApiUrl(String serverApiUrl) {
		SERVER_API_URL = serverApiUrl;
	}

	@Value("${server.h5.url:}")
	public void setServerH5Url(String serverH5Url) {
		SERVER_H5_URL = serverH5Url;
	}

	@Value("${server.itf.url:}")
	public void setServerItfUrl(String serverItfUrl) {
		SERVER_ITF_URL = serverItfUrl;
	}

	@Value("${oss.accesskey.id:}")
	public void setMoxieAccesskeyId(String moxieAccesskeyId) {
		MOXIE_ACCESSKEY_ID = moxieAccesskeyId;
	}

	@Value("${oss.accesskey.secret:}")
	public void setMoxieAccessKeySecret(String moxieAccessKeySecret) {
		MOXIE_ACCESS_KEY_SECRET = moxieAccessKeySecret;
	}

	@Value("${moxie.magic.customer.id:}")
	public void setMoxieCustomerId(String moxieCustomerId) {
		MOXIE_CUSTOMER_ID = moxieCustomerId;
	}

	@Value("${moxie.magic.secret:}")
	public void setMoxieMagicSecret(String moxieMagicSecret) {
		MOXIE_MAGIC_SECRET = moxieMagicSecret;
	}

	@Value("${moxie.zm.score:}")
	public void setMoxieZmScore(String moxieZmScore) {
		MOXIE_ZM_SCORE = moxieZmScore;
	}

	@Value("${moxie.magic.token:}")
	public void setMoxieMagicToken(String moxieMagicToken) {
		MOXIE_MAGIC_TOKEN = moxieMagicToken;
	}

	@Value("${moxie.magic.report:}")
	public void setMoxieMagicReport(String moxieMagicReport) {
		MOXIE_MAGIC_REPORT = moxieMagicReport;
	}

	@Value("${moxie.jxreport:}")
	public void setMoxieJxreport(String moxieJxreport) {
		MOXIE_JXREPORT = moxieJxreport;
	}

	@Value("${moxie.mxdata.ex:}")
	public void setMoxieMxdataEx(String moxieMxdataEx) {
		MOXIE_MXDATA_EX = moxieMxdataEx;
	}

	@Value("${moxie.alipay:}")
	public void setMoxieAlipay(String moxieAlipay) {
		MOXIE_ALIPAY = moxieAlipay;
	}

	@Value("${oss.endpoint.out:}")
	public void setOosEndpointOut(String oss_endpoint_out) {
		OSS_ENPOINT_OUT = oss_endpoint_out;
	}
	
	@Value("${oss.endpoint.in:}")
	public void setOosEndpointIn(String oss_endpoint_in) {
		OSS_ENPOINT_IN = oss_endpoint_in;
	}

	@Value("${oss.moxie.bucket.name:}")
	public void setOSS_MOXIE_BUCKET_NAME(String oSS_MOXIE_BUCKET_NAME) {
		OSS_MOXIE_BUCKET_NAME = oSS_MOXIE_BUCKET_NAME;
	}

	@Value("${oss.moxie.bucket.name.mobile_jxl:}")
	public void setOSS_MOXIE_MOBILE_JXL(String oSS_MOXIE_MOBILE_JXL) {
		OSS_MOXIE_MOBILE_JXL = oSS_MOXIE_MOBILE_JXL;
	}

	@Value("${oss.moxie.bucket.name.mobile_mxdata:}")
	public void setOSS_MOXIE_MOBILE_MXDATA(String oSS_MOXIE_MOBILE_MXDATA) {
		OSS_MOXIE_MOBILE_MXDATA = oSS_MOXIE_MOBILE_MXDATA;
	}

	@Value("${oss.moxie.bucket.name.zfb_data:}")
	public void setOSS_MOXIE_ZFB_DATA(String oSS_MOXIE_ZFB_DATA) {
		OSS_MOXIE_ZFB_DATA = oSS_MOXIE_ZFB_DATA;
	}

	@Value("${oss.moxie.bucket.name.magic_wand:}")
	public void setOSS_MOXIE_MAGIC_WAND(String oSS_MOXIE_MAGIC_WAND) {
		OSS_MOXIE_MAGIC_WAND = oSS_MOXIE_MAGIC_WAND;
	}

	@Value("${oss.moxie.bucket.name.address_list:}")
	public void setOSS_MOXIE_ADDRESS_LIST(String oSS_MOXIE_ADDRESS_LIST) {
		OSS_MOXIE_ADDRESS_LIST = oSS_MOXIE_ADDRESS_LIST;
	}

}
