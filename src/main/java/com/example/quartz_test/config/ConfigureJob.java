package com.example.quartz_test.config;

import java.util.ArrayList;
import java.util.List;

import com.example.quartz_test.job.RandomNumberPrintJob;
import com.example.quartz_test.job.TimePrintJob;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigureJob {


	/**
	 * 추후 Dynamic하게 Bean Trigger 생성 할때 필요
	 *
	@Autowired
	private List<Job> quartJobList;

	@Bean
	public List<JobDetail> jobADetails() {

		List<JobDetail> jobDetailList = new ArrayList<>();
		for (Job job : quartJobList) {
			Class clazz = job.getClass();
			jobDetailList.add(JobBuilder.newJob(clazz)
					.withIdentity(clazz.getName() + "_jobDetail")
					.storeDurably().build());
		}

		return jobDetailList;
	}
	 */

	@Bean
	public JobDetail jobADetails() {
		return JobBuilder.newJob(RandomNumberPrintJob.class).withIdentity("sampleJobA")
				.storeDurably().build();
	}

	@Bean
	public Trigger jobATrigger(JobDetail jobADetails) {

		return TriggerBuilder.newTrigger().forJob(jobADetails)
				.withIdentity("sampleTriggerA")
				.withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * ? * * *"))
				.build();
	}


	@Bean
	public JobDetail jobBDetails() {
		return JobBuilder.newJob(TimePrintJob.class).withIdentity("sampleJobB")
				.storeDurably().build();
	}

	@Bean
	public Trigger jobBTrigger(JobDetail jobBDetails) {

		return TriggerBuilder.newTrigger().forJob(jobBDetails)
				.withIdentity("sampleTriggerB")
				.withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * ? * * *"))
				.build();
	}

}
