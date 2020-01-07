package com.example.quartz_test.listener;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JobsListener implements org.quartz.JobListener {

	@Override
	public String getName() {
		return "JobsLister";
	}


	//실행 전
	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
//		JobKey jobKey = context.getJobDetail().getKey();
//		log.info("jobToBeExecuted :: jobKey : {}", jobKey);
	}

	//??
	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
//		JobKey jobKey = context.getJobDetail().getKey();
//		log.info("jobExecutionVetoed :: jobKey : {}", jobKey);
	}

	//실행 후
	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
//		JobKey jobKey = context.getJobDetail().getKey();
//		log.info("jobWasExecuted :: jobKey : {}", jobKey);
	}
}
