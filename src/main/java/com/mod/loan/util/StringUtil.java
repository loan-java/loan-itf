package com.mod.loan.util;

import java.util.Date;

public class StringUtil {

    /**
     * 根据前缀生成订单编号
     *
     * @return
     */
    public static String getFlowNo(String prefix) {
        // TODO 目前生成规则为：日期精确到毫秒
        return prefix + TimeUtils.parseTime(new Date(), TimeUtils.dateformat6) + RandomUtils.generateRandomNum(6);
    }

}
