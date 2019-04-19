package com.mod.loan.service;

import com.mod.loan.common.mapper.BaseService;
import com.mod.loan.model.MoxieMobile;
import com.mod.loan.model.MoxieZfb;
import com.mod.loan.model.User;
import com.mod.loan.model.UserIdent;

public interface UserService extends BaseService< User,Long>{

     void updateUserZfb(UserIdent userIdent,MoxieZfb moxieZfb);
     
     void updateUserMobile(UserIdent userIdent,MoxieMobile moxieMobile);
}
