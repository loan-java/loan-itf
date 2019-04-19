package com.mod.loan.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.mod.loan.common.mapper.BaseService;
import com.mod.loan.model.RecycleOrderExport;
import com.mod.loan.model.form.OrderQuery;

public interface RecycleOrderExportService extends BaseService<RecycleOrderExport, Long> {

	/**
	 * 催单excel相关信息查询
	 */
	List<Map<String, Object>> selectOverdueUserMessage(OrderQuery query);

	/**
	 * 催单excel上传并更新催单导出任务表记录
	 * 
	 * @param recordId
	 * @param workbook
	 */
	void updateExportRecordsAnduploadExcel(RecycleOrderExport record, HSSFWorkbook workbook);

	List<RecycleOrderExport> findByStatus(int status);
}
