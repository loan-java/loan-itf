package com.mod.loan.mapper;

import org.apache.ibatis.annotations.Param;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.ReportPartnerEffect;

public interface ReportPartnerEffectMapper extends MyBaseMapper<ReportPartnerEffect> {

	void reportPartnerEffect(@Param("date") String date, @Param("day") Integer day, @Param("merchant") String merchant);
}