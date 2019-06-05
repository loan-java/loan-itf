package com.mod.loan.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.net.URLDecoder;

/**
 * @author kk
 */
@Configuration
public class Constant {

    public static final String KUAI_QIAN_UID_PFX = "JSD";

    public static String ENVIROMENT;
    public static String baoFooKeyStorePath;
    public static String baoFooPubKeyPath;
    public static String baoFooVersion;
    public static String baoFooTerminalId;
    public static String baoFooMemberId;
    public static String baoFooRepayUrl;
    public static String baoFooKeyStorePassword;

    public static String kuaiQianRepayUrl;
    public static String kuaiQianTerminalId;
    public static String kuaiQianMemberId;
    public static String kuaiQianVersion;
    public static String kuaiQianJksPath;
    public static String kuaiQianKeyPassword;


    public static String smsType;

    public static String juheCallBackUrl;


    public static String rongZeRequestAppId;
    public static String rongZeCallbackUrl;
    public static String rongZeQueryUrl;
    public static String rongZePublicKey;

    public static String orgPrivateKey;


    public static String OSS_STATIC_BUCKET_NAME;
    public static String OSS_STATIC_BUCKET_NAME_MOBILE;
    public static String OSS_ACCESSKEY_ID;
    public static String OSS_ENDPOINT_IN;
    public static String OSS_ACCESS_KEY_SECRET;

    @Value("${rongze.request.app.id}")
    public void setRongZeRequestAppId(String rongZeRequestAppId) {
        Constant.rongZeRequestAppId = rongZeRequestAppId;
    }

    @Value("${rongze.callback.url}")
    public void setRongZeCallbackUrl(String rongZeCallbackUrl) {
        Constant.rongZeCallbackUrl = rongZeCallbackUrl;
    }

    @Value("${rongze.query.url}")
    public void setRongZeQueryUrl(String rongZeQueryUrl) {
        Constant.rongZeQueryUrl = rongZeQueryUrl;
    }

    @Value("${org.rsa.private.key}")
    public void setOrgPrivateKey(String orgPrivateKey) {
        Constant.orgPrivateKey = orgPrivateKey;
    }

    @Value("${rongze.rsa.public.key}")
    public void setRongZePublicKey(String rongZePublicKey) {
        Constant.rongZePublicKey = rongZePublicKey;
    }

    @Value("${sms.type}")
    public void setSmsType(String smsType) {
        if(StringUtils.startsWith(smsType, "%")){
            try {
                smsType = URLDecoder.decode(smsType, "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Constant.smsType = smsType;
    }

    @Value("${kuaiqian.jks.path}")
    public void setKuaiQianJksPath(String kuaiQianJksPath) {
        Constant.kuaiQianJksPath = kuaiQianJksPath;
    }

    @Value("${kuaiqian.key.password}")
    public void setKuaiQianKeyPassword(String kuaiQianKeyPassword) {
        Constant.kuaiQianKeyPassword = kuaiQianKeyPassword;
    }

    @Value("${kuaiqian.version}")
    public void setKuaiQianVersion(String kuaiQianVersion) {
        Constant.kuaiQianVersion = kuaiQianVersion;
    }

    @Value("${kuaiqian.repay.url}")
    public void setKuaiQianRepayUrl(String kuaiQianRepayUrl) {
        Constant.kuaiQianRepayUrl = kuaiQianRepayUrl;
    }

    @Value("${kuaiqian.terminal.id}")
    public void setKuaiQianTerminalId(String kuaiQianTerminalId) {
        Constant.kuaiQianTerminalId = kuaiQianTerminalId;
    }

    @Value("${kuaiqian.member.id}")
    public void setKuaiQianMemberId(String kuaiQianMemberId) {
        Constant.kuaiQianMemberId = kuaiQianMemberId;
    }

    @Value("${environment:}")
    public void setENVIROMENT(String environment) {
        Constant.ENVIROMENT = environment;
    }

    @Value("${baofoo.key.store.path}")
    public void setBaoFooKeyStorePath(String baoFooKeyStorePath) {
        Constant.baoFooKeyStorePath = baoFooKeyStorePath;
    }

    @Value("${baofoo.key.store.password}")
    public void setBaoFooKeyStorePassword(String baoFooKeyStorePassword) {
        Constant.baoFooKeyStorePassword = baoFooKeyStorePassword;
    }

    @Value("${baofoo.pub.key.path}")
    public void setBaoFooPubKeyPath(String baoFooPubKeyPath) {
        Constant.baoFooPubKeyPath = baoFooPubKeyPath;
    }

    @Value("${baofoo.repay.url}")
    public void setBaoFooRepayUrl(String baoFooRepayUrl) {
        Constant.baoFooRepayUrl = baoFooRepayUrl;
    }

    @Value("${baofoo.member.id}")
    public void setBaoFooMemberId(String baoFooMemberId) {
        Constant.baoFooMemberId = baoFooMemberId;
    }

    @Value("${baofoo.terminal.id}")
    public void setBaoFooTerminalId(String baoFooTerminalId) {
        Constant.baoFooTerminalId = baoFooTerminalId;
    }

    @Value("${baofoo.version}")
    public void setBaoFooVersion(String baoFooVersion) {
        Constant.baoFooVersion = baoFooVersion;
    }

    @Value("${juhe.call.back.url}")
    public void setJuheCallBackUrl(String juheCallBackUrl) {
        Constant.juheCallBackUrl = juheCallBackUrl;
    }

    @Value("${oss.static.bucket.name:}")
    public void setOSS_STATIC_BUCKET_NAME(String oSS_STATIC_BUCKET_NAME) {
        OSS_STATIC_BUCKET_NAME = oSS_STATIC_BUCKET_NAME;
    }

    @Value("${oss.static.bucket.name.mobile:}")
    public void setOSS_STATIC_BUCKET_NAME_MOBILE(String oSS_STATIC_BUCKET_NAME_MOBILE) {
        OSS_STATIC_BUCKET_NAME_MOBILE = oSS_STATIC_BUCKET_NAME_MOBILE;
    }

    @Value("${oss.accesskey.id:}")
    public void setOSS_ACCESSKEY_ID(String oSS_ACCESSKEY_ID) {
        OSS_ACCESSKEY_ID = oSS_ACCESSKEY_ID;
    }

    @Value("${oss.accesskey.secret:}")
    public void setOSS_ACCESS_KEY_SECRET(String oSS_ACCESS_KEY_SECRET) {
        OSS_ACCESS_KEY_SECRET = oSS_ACCESS_KEY_SECRET;
    }

    @Value("${oss.endpoint.in}")
    public void setOssEndpointIn(String ossEndpointIn) {
        Constant.OSS_ENDPOINT_IN = ossEndpointIn;
    }

}
