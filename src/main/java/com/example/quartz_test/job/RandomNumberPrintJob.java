package com.example.quartz_test.job;

import com.example.quartz_test.service.RandomNumberPrintService;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class RandomNumberPrintJob implements Job {

	@Autowired
	private RandomNumberPrintService randomNumberPrintService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		randomNumberPrintService.printRandomNumber();

	}
}
