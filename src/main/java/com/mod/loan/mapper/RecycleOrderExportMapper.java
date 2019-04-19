package com.mod.loan.mapper;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.RecycleOrderExport;
import com.mod.loan.model.form.OrderQuery;

public interface RecycleOrderExportMapper extends MyBaseMapper<RecycleOrderExport> {

	/**
	 * 催单excel导出
	 */
	List<Map<String, Object>> selectOverdueUserMessage(OrderQuery query);

	List<RecycleOrderExport> findByStatus(int status);
}