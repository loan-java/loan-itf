package com.mod.loan.mapper;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.ReportOrderLoan;

public interface ReportOrderLoanMapper extends MyBaseMapper<ReportOrderLoan> {
	
	void reportOrderLoan(String date);
}