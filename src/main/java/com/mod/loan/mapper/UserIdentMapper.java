package com.mod.loan.mapper;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.UserIdent;

public interface UserIdentMapper extends MyBaseMapper<UserIdent> {
    
	/**
	 * 运营商认证失效
	 * @param date
	 */
    int expireUserIdentMobile(String date);
    /**
	 * 支付宝认证失效
	 * @param date
	 */
    int expireUserIdentZfb(String date);

}