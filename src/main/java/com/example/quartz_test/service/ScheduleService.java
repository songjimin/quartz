package com.example.quartz_test.service;

import java.util.List;

import com.example.quartz_test.job.MisfiredTestJob;
import com.example.quartz_test.job.RandomNumberPrintJob;
import com.example.quartz_test.job.TimePrintJob;
import com.example.quartz_test.model.TargetServerInfo;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class ScheduleService {

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	public void setJobs() throws SchedulerException {


//		schedulerFactoryBean.getScheduler().deleteJob(new JobKey("DEFAULT.sampleJobA"));

		//TODO DB에서 데이터 가지고와 Job과 Trigger셋팅 로직 필요
		List<TargetServerInfo> targetServerInfoList = getTargetServerInfoList();

		schedulerFactoryBean.getScheduler().deleteJob(new JobKey("1.11"));
		schedulerFactoryBean.getScheduler().deleteJob(new JobKey("1.12"));


		for (TargetServerInfo targetServerInfo : targetServerInfoList) {

			JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) targetServerInfo.getJobClass())
											.withIdentity(String.valueOf(targetServerInfo.getConnectionTargetId()), String.valueOf(targetServerInfo.getGameCode()))
											.storeDurably()
											.build();

			Trigger trigger =  TriggerBuilder.newTrigger().forJob(jobDetail)
												.withIdentity(String.valueOf(targetServerInfo.getConnectionTargetId()), String.valueOf(targetServerInfo.getGameCode()))
												.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(Integer.valueOf(targetServerInfo.getInterval())))
												.usingJobData("targetServerAddress", targetServerInfo.getTargetServerAddress())
												.usingJobData("targetServerPort", targetServerInfo.getTargetServerPort())
												.usingJobData("interval", targetServerInfo.getInterval())
												.build();

			if (schedulerFactoryBean.getScheduler().checkExists(trigger.getJobKey())) {
				schedulerFactoryBean.getScheduler().deleteJob(trigger.getJobKey());
			}
			schedulerFactoryBean.getScheduler().scheduleJob(jobDetail, trigger);
		}
	}

	private List<TargetServerInfo> getTargetServerInfoList() {

		TargetServerInfo targetServerInfo = TargetServerInfo.builder()
															.connectionTargetId(10)
															.gameCode("L2M")
															.interval("1")
															.targetServerAddress("127.0.0.1")
															.targetServerPort(8088)
															.jobClass(RandomNumberPrintJob.class)
															.build();

		TargetServerInfo targetServerInfo2 = TargetServerInfo.builder()
															.connectionTargetId(22)
															.gameCode("BNS")
															.interval("3")
															.targetServerAddress("127.0.0.2")
															.targetServerPort(8089)
															.jobClass(TimePrintJob.class)
															.build();

		TargetServerInfo targetServerInfo3 = TargetServerInfo.builder()
				.connectionTargetId(22)
				.gameCode("BNS2")
				.interval("1")
				.targetServerAddress("127.0.0.2")
				.targetServerPort(8089)
				.jobClass(MisfiredTestJob.class)
				.build();

		return List.of(targetServerInfo, targetServerInfo2);
	}
}
