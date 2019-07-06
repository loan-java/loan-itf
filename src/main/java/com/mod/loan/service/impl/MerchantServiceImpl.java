package com.mod.loan.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.config.redis.RedisMapper;
import com.mod.loan.mapper.MerchantMapper;
import com.mod.loan.model.Merchant;
import com.mod.loan.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantServiceImpl extends BaseServiceImpl<Merchant, String> implements MerchantService {


    @Autowired
    private RedisMapper redisMapper;

    @Autowired
    MerchantMapper merchantMapper;

    @Override
    public List<Merchant> selectMerchantAliasByStatus(int status) {
        return merchantMapper.selectMerchantAliasByStatus(status);
    }

    @Override
    public Merchant findMerchantByAlias(String merchantAlias) {
        Merchant merchant = null;
        if (redisMapper.hasKey("merchant:" + merchantAlias)) {
            merchant = redisMapper.get("merchant:" + merchantAlias, new TypeReference<Merchant>() {
            });
        }
        if (merchant == null || merchant.getBindType() == null || merchant.getRiskType() == null || merchant.getPaymentType() == null) {
            merchant = merchantMapper.selectByPrimaryKey(merchantAlias);
            if (merchant != null) {
                redisMapper.set("merchant:" + merchantAlias, merchant, 600);
            }
        }
        return merchant;
    }

}
