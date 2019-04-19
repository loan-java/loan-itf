package com.mod.loan.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.mapper.MoxieMobileMapper;
import com.mod.loan.mapper.MoxieZfbMapper;
import com.mod.loan.mapper.UserIdentMapper;
import com.mod.loan.model.MoxieMobile;
import com.mod.loan.model.MoxieZfb;
import com.mod.loan.model.User;
import com.mod.loan.model.UserIdent;
import com.mod.loan.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

	@Autowired
	UserIdentMapper identMapper;
	@Autowired
	MoxieZfbMapper moxieZfbMapper;
	@Autowired
	MoxieMobileMapper moxieMobileMapper;

	@Override
	public void updateUserZfb(UserIdent userIdent, MoxieZfb moxieZfb) {
		UserIdent old = identMapper.selectByPrimaryKey(userIdent.getUid());
		if (old.getAlipay() == 2) {
			return;
		}
		if (old.getAlipay() == userIdent.getAlipay()) {
			return;
		}
		// 当用户认证成功时，插入一条MoxieZfb记录
		if (userIdent.getAlipay() == 2) {
			moxieZfbMapper.insertSelective(moxieZfb);
		}
		userIdent.setAlipayTime(new Date());
		identMapper.updateByPrimaryKeySelective(userIdent);
	}

	@Override
	public void updateUserMobile(UserIdent userIdent, MoxieMobile moxieMobile) {
		UserIdent old = identMapper.selectByPrimaryKey(userIdent.getUid());
		if (old.getMobile() == 2) {
			return;
		}
		if (old.getMobile() == userIdent.getMobile()) {
			return;
		}
		userIdent.setMobileTime(new Date());
		identMapper.updateByPrimaryKeySelective(userIdent);
		if (moxieMobile != null) {
			moxieMobileMapper.insertSelective(moxieMobile);
		}
	}

}
