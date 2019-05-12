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


}
