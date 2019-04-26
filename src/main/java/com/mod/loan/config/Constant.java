package com.mod.loan.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author kk
 */
@Configuration
public class Constant {

    public static String ENVIROMENT;
    public static String baoFooKeyStorePath;
    public static String baoFooPubKeyPath;
    public static String baoFooVersion;
    public static String baoFooTerminalId;
    public static String baoFooMemberId;
    public static String baoFooRepayUrl;
    public static String baoFooKeyStorePassword;

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
