package com.mod.loan.mapper;

import org.apache.ibatis.annotations.Param;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.ReportRegisterOrder;

public interface ReportRegisterOrderMapper extends MyBaseMapper<ReportRegisterOrder> {

	void reportRegisterOrder(@Param("date") String date);
}