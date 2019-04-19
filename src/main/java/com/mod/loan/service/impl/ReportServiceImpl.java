package com.mod.loan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mod.loan.mapper.ReportOrderLoanMapper;
import com.mod.loan.mapper.ReportOrderRepayMapper;
import com.mod.loan.mapper.ReportPartnerEffectMapper;
import com.mod.loan.mapper.ReportRegisterOrderMapper;
import com.mod.loan.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportOrderLoanMapper reportOrderLoanMapper;
	@Autowired
	private ReportOrderRepayMapper reportOrderRepayMapper;
	@Autowired
	private ReportPartnerEffectMapper reportPartnerEffectMapper;
	@Autowired
	private ReportRegisterOrderMapper reportRegisterOrderMapper;

	@Override
	public void reportOrderRepay(String date, Integer day) {
		reportOrderRepayMapper.reportOrderRepay(date, day);
	}

	@Override
	public void reportOrderLoan(String date) {
		reportOrderLoanMapper.reportOrderLoan(date);
	}

	@Override
	public void reportPartnerEffect(String date, Integer day, String merchant) {
		reportPartnerEffectMapper.reportPartnerEffect(date, day, merchant);
	}

	@Override
	public void reportRegisterOrder(String date) {
		reportRegisterOrderMapper.reportRegisterOrder(date);
	}

}
