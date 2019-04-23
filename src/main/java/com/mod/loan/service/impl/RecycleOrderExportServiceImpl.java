//package com.mod.loan.service.impl;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.joda.time.DateTime;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.aliyun.oss.OSSClient;
//import com.mod.loan.common.mapper.BaseServiceImpl;
//import com.mod.loan.config.Constant;
//import com.mod.loan.mapper.RecycleOrderExportMapper;
//import com.mod.loan.model.RecycleOrderExport;
//import com.mod.loan.model.form.OrderQuery;
//import com.mod.loan.service.RecycleOrderExportService;
//
//@Service
//public class RecycleOrderExportServiceImpl extends BaseServiceImpl<RecycleOrderExport, Long> implements RecycleOrderExportService {
//	private static Logger logger = LoggerFactory.getLogger(RecycleOrderExportServiceImpl.class);
//
//	private static final String visit_url_out = "https://static-ym.oss-cn-hangzhou.aliyuncs.com";
//	private static final String bucket_name = "static-ym";
//
//	@Autowired
//	private RecycleOrderExportMapper recycleOrderExportMapper;
//
//	@Override
//	public List<RecycleOrderExport> findByStatus(int status) {
//		return recycleOrderExportMapper.findByStatus(status);
//	}
//
//	@Override
//	public List<Map<String, Object>> selectOverdueUserMessage(OrderQuery query) {
//		return recycleOrderExportMapper.selectOverdueUserMessage(query);
//	}
//
//	@Override
//	public void updateExportRecordsAnduploadExcel(RecycleOrderExport record, HSSFWorkbook workbook) {
//		OSSClient ossClient = new OSSClient(endPointUrl(Constant.ENVIROMENT), Constant.MOXIE_ACCESSKEY_ID, Constant.MOXIE_ACCESS_KEY_SECRET);
//		DateTime currentDate = new DateTime();
//		String fileName = UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
//		String savePath = currentDate.getYear() + "/" + currentDate.toString("MMdd") + "/" + fileName;
//		try {
//			ByteArrayOutputStream os = new ByteArrayOutputStream();
//			workbook.write(os);
//			InputStream inputStream = new ByteArrayInputStream(os.toByteArray());
//			ossClient.putObject(bucket_name, savePath, inputStream);
//			// 上传完成后更新导出记录
//			String url = visit_url_out + "/" + savePath;
//			record.setUrl(url);
//			record.setStatus(1); // 状态：已完成
//			record.setUpdateTime(new Date());
//			recycleOrderExportMapper.updateByPrimaryKey(record);
//		} catch (Exception e) {
//			logger.error("催收订单excel上传失败,id={}", record.getId());
//			logger.error("催收订单excel上传失败", e);
//		} finally {
//			if (ossClient != null) {
//				ossClient.shutdown();
//			}
//		}
//	}
//
//	private static String endPointUrl(String env) {
//		if ("dev".equals(env)) {
//			return Constant.OSS_ENPOINT_OUT;
//		}
//		return Constant.OSS_ENPOINT_IN;
//	}
//
//}
