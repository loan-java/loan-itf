//package com.mod.loan.itf.moxie.task;
//
//import com.mod.loan.common.message.MoxieMobileMessage;
//import com.mod.loan.common.message.MoxieZfbMessage;
//import com.mod.loan.config.rabbitmq.RabbitConst;
//import com.mod.loan.mapper.MoxieMobileMapper;
//import com.mod.loan.mapper.MoxieZfbMapper;
//import com.mod.loan.model.MoxieMobile;
//import com.mod.loan.model.MoxieZfb;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class MoxieTask {
//
//	public static final Logger logger = LoggerFactory.getLogger(MoxieTask.class);
//
//	@Autowired
//	MoxieMobileMapper moxieMobileMapper;
//	@Autowired
//	MoxieZfbMapper moxieZfbMapper;
//	@Value("${moxie.token}")
//	String token;
//	@Value("${environment}")
//	String env;
//	@Autowired
//	RabbitTemplate rabbitTemplate;
//
//	/**
//	 * 每隔1分钟 重新发消息上传运营商数据
//	 */
//	@Scheduled(cron = "0 0/1 * * * ?")
//	public void moxieMobileTaskAsc() {
//		List<MoxieMobile> moxieMobileList = moxieMobileMapper.findMoxieMobileList(0, 100);
//		if (moxieMobileList.size() == 0) {
//			return;
//		}
//		moxieMobileList.forEach(item -> {
//			MoxieMobileMessage message = new MoxieMobileMessage();
//			message.setId(item.getId());
//			message.setTaskId(item.getTaskId());
//			rabbitTemplate.convertAndSend(RabbitConst.queue_moxie_mobile, message);
//		});
//	}
//
//	/**
//	 * 每隔1分钟 重新发消息上传支付宝数据
//	 */
//	@Scheduled(cron = "0 0/1 * * * ?")
//	public void moxieZfbTask() {
//		List<MoxieZfb> moxieZfbList = moxieZfbMapper.findMoxieZfbList(0, 100);
//		if (moxieZfbList.size() == 0) {
//			return;
//		}
//		moxieZfbList.forEach(item -> {
//			MoxieZfbMessage message = new MoxieZfbMessage();
//			message.setId(item.getId());
//			message.setTaskId(item.getTaskId());
//			rabbitTemplate.convertAndSend(RabbitConst.queue_moxie_zfb, message);
//		});
//	}
//}
