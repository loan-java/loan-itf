package com.mod.loan.config;

import java.util.concurrent.Executors;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
public class TaskConfig implements SchedulingConfigurer {

	/**
	 * 配置定时任务线程池大小
	 */
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(Executors.newScheduledThreadPool(10));

		//动态增加定时任务
//		String[] crons= {"0/2 * * * * ?","0/1 * * * * ?"};
//		for (int i = 0; i < crons.length; i++) {
//			final int a=i;
//			TriggerTask triggerTask = new TriggerTask(
//					() -> {
//						System.out.println("执行定时任务:"+a);
//						System.out.println(JSON.toJSONString(bannerService.selectAll()));
//					},
//					triggerContext -> {
//						return new CronTrigger(crons[a]).nextExecutionTime(triggerContext);
//					});
//			taskRegistrar.addTriggerTask(triggerTask);
//		}
	}
}