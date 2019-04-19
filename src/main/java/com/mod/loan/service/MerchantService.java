package com.mod.loan.service;

import java.util.List;

import com.mod.loan.common.mapper.BaseService;
import com.mod.loan.model.Merchant;

public interface MerchantService extends BaseService<Merchant, String> {

	List<Merchant> selectMerchantAliasByStatus(int status);
}
