package com.example.quartz_test.service;

import org.quartz.SchedulerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;


@Service
public class SchedulerLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	ScheduleService scheduleService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		try {
			scheduleService.setJobs();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

	}
}
