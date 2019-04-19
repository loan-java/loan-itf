package com.mod.loan.mapper;

import org.apache.ibatis.annotations.Param;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.ReportOrderRepay;

public interface ReportOrderRepayMapper extends MyBaseMapper<ReportOrderRepay> {
	
	void reportOrderRepay(@Param("date")String date, @Param("day")Integer day);
}