package com.mod.loan.service.impl;

import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.mapper.MerchantMapper;
import com.mod.loan.model.Merchant;
import com.mod.loan.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantServiceImpl extends BaseServiceImpl<Merchant, String> implements MerchantService {

	@Autowired
	MerchantMapper merchantMapper;

	@Override
	public List<Merchant> selectMerchantAliasByStatus(int status) {
		return merchantMapper.selectMerchantAliasByStatus(status);
	}

}
