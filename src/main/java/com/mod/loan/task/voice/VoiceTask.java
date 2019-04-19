package com.mod.loan.task.voice;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mod.loan.common.enums.VoiceTemplate;
import com.mod.loan.common.message.QueueVoiceMessage;
import com.mod.loan.config.rabbitmq.RabbitConst;
import com.mod.loan.service.OrderService;

@Component
public class VoiceTask {

	public static final Logger logger = LoggerFactory.getLogger(VoiceTask.class);
	@Autowired
	OrderService orderService;
	@Autowired
	RabbitTemplate rabbitTemplate;

	/**
	 * 今日还款语音提醒
	 * 中午12点
	 */
	@Scheduled(cron = "0 0 12 * * ?")
	public void overdueToday() {
		try {
			List<Map<String,Object>> orderList = orderService.findByStatusAndOverdays(31,"=",0);
			orderList.stream().forEach(order -> {
				QueueVoiceMessage voiceMessage = new QueueVoiceMessage();
				voiceMessage.setType(VoiceTemplate.V10001.getKey());
				voiceMessage.setPhone(order.get("userPhone").toString());
				voiceMessage.setClientAlias(order.get("merchant").toString());
				voiceMessage.setUid(order.get("uid").toString());
				voiceMessage.setOrderId(order.get("orderId").toString());
				voiceMessage.setParams(order.get("userName").toString()+"|"+order.get("merchantApp").toString());
				rabbitTemplate.convertAndSend(RabbitConst.queue_voice, voiceMessage);
			});

		} catch (Exception e) {
			logger.error("今日逾期voice发送异常", e);
		}
	}




}
