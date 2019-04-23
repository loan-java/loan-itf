//package com.mod.loan.task.recycle;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.mod.loan.model.RecycleOrderExport;
//import com.mod.loan.model.form.OrderQuery;
//import com.mod.loan.model.moxie.ContactList;
//import com.mod.loan.model.moxie.Linkmans;
//import com.mod.loan.service.RecycleOrderExportService;
//import com.mod.loan.util.AddressListUtil;
//import com.mod.loan.util.CheckUtils;
//import com.mod.loan.util.ExcelUtil;
//import com.mod.loan.util.MoxieOssUtil;
//
//@Component
//public class RecycleExcelTask {
//
//	public static final Logger logger = LoggerFactory.getLogger(RecycleExcelTask.class);
//
//	@Autowired
//	private RecycleOrderExportService recycleOrderExportService;
//
//	@Scheduled(cron = "0 0/1 * * * ?")
//	public void recycleExcelTask() {
//		List<RecycleOrderExport> exports = recycleOrderExportService.findByStatus(0);
//		if (exports.size() < 1) {
//			return;
//		}
//		exports.forEach(item -> {
//			try {
//				recycle_export(item);
//			} catch (Exception e) {
//				logger.error("上传催收订单excel文件失败=" + JSON.toJSONString(item), e);
//			}
//		});
//	}
//
//	private void recycle_export(RecycleOrderExport recycleOrderExport) {
//		OrderQuery query = JSONObject.parseObject(recycleOrderExport.getParam(), OrderQuery.class);
//		List<Map<String, Object>> list = recycleOrderExportService.selectOverdueUserMessage(query);
//		list.parallelStream().forEach(item -> {
//			// 获取通话记录
//			String carrierReport = MoxieOssUtil.mobileJxlReport((String) item.get("mobileTaskId"));
//			String contact_list = JSONObject.parseObject(carrierReport).getString("contact_list");
//			// 获取用户通讯录
//			String address = MoxieOssUtil.addressList((String) item.get("addressListTaskId"));
//			List<Linkmans> addressList = AddressListUtil.getLinkmans(address);
//			Map<String, String> map = AddressListUtil.getMap(addressList);
//			// 匹配通话次数前二十的号码在通讯录中的名称
//			List<ContactList> listContactList = JSON.parseArray(contact_list, ContactList.class);
//			listContactList = listContactList.stream().filter(i -> CheckUtils.isMobiPhoneNum(i.getPhone_num())).collect(Collectors.toList());
//			if (listContactList.size() > 20) {
//				listContactList = listContactList.subList(0, 20);
//			}
//			Map<String, String> userAddressTop = new HashMap<>();
//			listContactList.parallelStream().forEach(callList -> {
//				if (callList != null) {
//					String name = map.get(callList.getPhone_num());// 姓名
//					userAddressTop.put(callList.getPhone_num(), name == null ? "未知" : name);
//				}
//			});
//			item.put("userAddress", JSONObject.toJSONString(userAddressTop));
//		});
//		String[] title = null;
//        String sheetName = null;
//        String[] columns = null;
//		title = new String[] { "申贷日期", "用户姓名", "用户手机", "身份证号", "产品名称", "放款金额", "逾期金额", "应还本金", "应还利息", "应还罚息", "联系人1姓名", "联系人1关系", "联系人1电话", "联系人2姓名", "联系人2关系", "联系人2电话", "通讯录"};
//		sheetName = "订单信息";
//		columns = new String[] { "create_time", "user_name", "user_phone", "user_cert_no", "merchant", "borrow_money", "overdue_fee", "actual_money", "should_repay_interests", "should_repay_penalty", "direct_contact_name", "direct_contact", "direct_contact_phone", "others_contact_name", "others_contact", "others_contact_phone", "userAddress" };
//		HSSFWorkbook workbook = new HSSFWorkbook();
//		ExcelUtil.createSheet(workbook, sheetName, title, ExcelUtil.mapToArray(list, columns));
//		recycleOrderExportService.updateExportRecordsAnduploadExcel(recycleOrderExport, workbook);
//	}
//
//}
