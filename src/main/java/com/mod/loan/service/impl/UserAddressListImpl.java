package com.mod.loan.service.impl;

import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.mapper.UserAddressListMapper;
import com.mod.loan.model.UserAddressList;
import com.mod.loan.service.UserAddressListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserAddressListImpl extends BaseServiceImpl<UserAddressList, Long>implements UserAddressListService {

	@Autowired
	UserAddressListMapper userAddressListMapper;

	@Override
	public List<UserAddressList> findByStatus(Integer status) {
		return userAddressListMapper.findByStatus(status);
	}

	@Override
	public int updateTaskIdByUid(Long uid,String taskId) {
		return userAddressListMapper.updateTaskIdByUid(uid,taskId);
	}

}
