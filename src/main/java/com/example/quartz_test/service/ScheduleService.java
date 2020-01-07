package com.example.quartz_test.service;

import java.util.List;

import com.example.quartz_test.job.RandomNumberPrintJob;
import com.example.quartz_test.job.TimePrintJob;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
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

		//TODO DB에서 데이터 가지고와 Job과 Trigger셋팅 후, Scheduler에 저장
		JobDetail jobADetailA = JobBuilder.newJob(RandomNumberPrintJob.class)
											.withIdentity("sampleJobA")
											.usingJobData("Test", "ABCD")
											.storeDurably().build();

		Trigger triggerA =  TriggerBuilder.newTrigger().forJob(jobADetailA)
				.withIdentity("sampleTriggerA")
				.withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * ? * * *"))
				.build();


		JobDetail jobADetailB = JobBuilder.newJob(TimePrintJob.class)
										.withIdentity("sampleJobB")
										.storeDurably().build();

		Trigger triggerB =  TriggerBuilder.newTrigger().forJob(jobADetailB)
				.withIdentity("sampleTriggerB")
				.withSchedule(CronScheduleBuilder.cronSchedule("0/4 * * ? * * *"))
				.build();

		schedulerFactoryBean.getScheduler().scheduleJob(jobADetailA, triggerA);
		schedulerFactoryBean.getScheduler().scheduleJob(jobADetailB, triggerB);

	}
}
