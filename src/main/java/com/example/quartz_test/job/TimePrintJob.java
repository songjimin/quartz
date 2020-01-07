package com.example.quartz_test.job;

import com.example.quartz_test.service.TimePrintService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimePrintJob implements Job {

	@Autowired
	private TimePrintService timePrintService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		timePrintService.printTime();
	}
}
