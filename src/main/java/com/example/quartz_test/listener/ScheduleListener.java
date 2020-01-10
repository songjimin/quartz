package com.example.quartz_test.listener;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.SchedulerListener;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ScheduleListener implements SchedulerListener {


	@Override
	public void jobScheduled(Trigger trigger) {
		log.info("ScheduleListener::jobScheduled");
	}

	@Override
	public void jobUnscheduled(TriggerKey triggerKey) {
		log.info("ScheduleListener::jobUnscheduled");
	}

	@Override
	public void triggerFinalized(Trigger trigger) {
		log.info("ScheduleListener::triggerFinalized");
	}

	@Override
	public void triggerPaused(TriggerKey triggerKey) {
		log.info("ScheduleListener::triggerPaused");
	}

	@Override
	public void triggersPaused(String triggerGroup) {
		log.info("ScheduleListener::triggersPaused");
	}

	@Override
	public void triggerResumed(TriggerKey triggerKey) {
		log.info("ScheduleListener::triggerResumed");
	}

	@Override
	public void triggersResumed(String triggerGroup) {
		log.info("ScheduleListener::triggersResumed");
	}

	@Override
	public void jobAdded(JobDetail jobDetail) {
		log.info("ScheduleListener::jobAdded");
	}

	@Override
	public void jobDeleted(JobKey jobKey) {
		log.info("ScheduleListener::jobDeleted");
	}

	@Override
	public void jobPaused(JobKey jobKey) {
		log.info("ScheduleListener::jobPaused");
	}

	@Override
	public void jobsPaused(String jobGroup) {
		log.info("ScheduleListener::jobsPaused");
	}

	@Override
	public void jobResumed(JobKey jobKey) {
		log.info("ScheduleListener::jobResumed");
	}

	@Override
	public void jobsResumed(String jobGroup) {
		log.info("ScheduleListener::jobsResumed");
	}

	@Override
	public void schedulerError(String msg, SchedulerException cause) {
		log.info("ScheduleListener::schedulerError");
	}

	@Override
	public void schedulerInStandbyMode() {
		log.info("ScheduleListener::schedulerInStandbyMode");
	}

	@Override
	public void schedulerStarted() {
		log.info("ScheduleListener::schedulerStarted");
	}

	@Override
	public void schedulerStarting() {
		log.info("ScheduleListener::schedulerStarting");
	}

	@Override
	public void schedulerShutdown() {
		log.info("ScheduleListener::schedulerShutdown");
	}

	//Exception 받고 일루옴.
	@Override
	public void schedulerShuttingdown() {
		log.info("ScheduleListener::schedulerShuttingdown");
	}

	@Override
	public void schedulingDataCleared() {
		log.info("ScheduleListener::schedulingDataCleared");
	}
}
