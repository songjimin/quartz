package com.example.quartz_test.listener;

import com.example.quartz_test.service.JobHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JobsListener implements org.quartz.JobListener {

	@Autowired
	private JobHistoryService jobHistoryService;

	@Override
	public String getName() {
		return "JobListener::JobsLister";
	}

	//실행 전
	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		JobKey jobKey = context.getJobDetail().getKey();

		log.info("JobListener::jobToBeExecuted :: jobKey : {}", jobKey);
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
		JobKey jobKey = context.getJobDetail().getKey();
		log.info("JobListener::jobExecutionVetoed :: jobKey : {}", jobKey);
	}

	//실행 후  jobException을 통해 에러인지 확인 가능
	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		JobKey jobKey = context.getJobDetail().getKey();
		log.info("JobListener::jobWasExecuted :: jobKey : {}", jobKey);
//		jobHistoryService.saveTriggerHistoryInfo("TRIGGER", "success", jobKey, trigger);
	}
}
