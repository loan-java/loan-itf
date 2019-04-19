package com.mod.loan.task.user;

import com.alibaba.fastjson.JSON;
import com.mod.loan.model.UserAddressList;
import com.mod.loan.service.UserAddressListService;
import com.mod.loan.util.MoxieOssUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class AddressListTask {

	public static final Logger logger = LoggerFactory.getLogger(AddressListTask.class);

	@Autowired
	private UserAddressListService userAddressListService;
	@Value("${moxie.token}")
	String token;

	/**
	 * 查找状态为2的通讯录列表，上传阿里云后，更改状态，并标识taskId
	 */
	@Scheduled(cron = "*/5 * * * * ?")
	public void addressListTask() {
		List<UserAddressList> addressList = userAddressListService.findByStatus(2);
		if(addressList.size()==0){
			return;
		}
		addressList.forEach(item ->{
			try {
				String taskId = UUID.randomUUID().toString().replaceAll("-", "");
				MoxieOssUtil.uploaAddressList(taskId, item.getAddressList());
				userAddressListService.updateTaskIdByUid(item.getUid(),taskId);
//				logger.info("上传通讯录数据成功，id={},task_id={}",item.getId(),item.getTaskId());
			} catch (Exception e) {
				// TODO: handle exception
				logger.error("上传通讯录数据失败="+ JSON.toJSONString(item),e);
			}
		});
	}
}
