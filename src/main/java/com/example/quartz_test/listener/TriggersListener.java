package com.example.quartz_test.listener;

import com.example.quartz_test.service.JobHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TriggersListener implements TriggerListener {

	@Autowired
	private JobHistoryService jobHistoryService;

	@Override
	public String getName() {
		return "GLOBAL-TriggersListener";
	}

	@Override
	public void triggerFired(Trigger trigger, JobExecutionContext context) {
		JobKey jobKey = trigger.getJobKey();
		log.info("TriggerListener::triggerFired at {} :: jobKey : {}", trigger.getStartTime(), jobKey);
	}

	@Override
	public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
		JobKey jobKey = trigger.getJobKey();
		log.info("TriggerListener::vetoJobExecution at {} :: jobKey : {}", trigger.getStartTime(), jobKey);
		return false;
	}

	@Override
	public void triggerMisfired(Trigger trigger) {
		JobKey jobKey = trigger.getJobKey();
		log.info("TriggerListener::triggerMisfired at {} :: jobKey : {}", trigger.getStartTime(), jobKey);
//		jobHistoryService.saveTriggerHistoryInfo("misfire", jobKey, trigger);
	}


	@Override
	public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {
		JobKey jobKey = trigger.getJobKey();
		log.info("TriggerListener::triggerComplete at {} :: jobKey : {}", trigger.getStartTime(), jobKey);
//		jobHistoryService.saveTriggerHistoryInfo("success", jobKey, trigger);
	}
}
