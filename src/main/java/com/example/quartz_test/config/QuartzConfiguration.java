package com.example.quartz_test.config;

import java.util.Properties;

import javax.sql.DataSource;

import com.example.quartz_test.job.RandomNumberPrintJob;
import com.example.quartz_test.listener.JobsListener;
import com.example.quartz_test.listener.ScheduleListener;
import com.example.quartz_test.listener.TriggersListener;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobListener;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Slf4j
@Configuration
public class QuartzConfiguration {

	@Autowired
	private JobsListener jobsListener;

	@Autowired
	private TriggersListener triggersListener;

	@Autowired
	private ScheduleListener scheduleListener;

	@Autowired
	private QuartzProperties quartzProperties;

	@Autowired
	private DataSource dataSource;

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(ApplicationContext applicationContext) throws SchedulerException {

		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();

		AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
		jobFactory.setApplicationContext(applicationContext);
		schedulerFactoryBean.setJobFactory(jobFactory);

		schedulerFactoryBean.setApplicationContext(applicationContext);

		Properties properties = new Properties();
		properties.putAll(quartzProperties.getProperties());

		schedulerFactoryBean.setDataSource(dataSource);
		schedulerFactoryBean.setGlobalTriggerListeners(triggersListener);
		schedulerFactoryBean.setGlobalJobListeners(jobsListener);
		schedulerFactoryBean.setSchedulerListeners(scheduleListener);
		schedulerFactoryBean.setOverwriteExistingJobs(true); 		//DB업데이트 시, 업데이트된 내용 반영
		schedulerFactoryBean.setQuartzProperties(properties);
		schedulerFactoryBean.setWaitForJobsToCompleteOnShutdown(true);
		return schedulerFactoryBean;
	}


	//TODO https://helloino.tistory.com/138
	//TODO https://kwonnam.pe.kr/wiki/springframework/bean
	@Bean
	public SmartLifecycle gracefulShutdownHookForQuartz(SchedulerFactoryBean schedulerFactoryBean) {
		return new SmartLifecycle() {
			private boolean isRunning = false;
			@Override
			public boolean isAutoStartup() {
				return true;
			}

			@Override
			public void stop(Runnable callback) {
				stop();
				log.info("GraceFul::Spring container is shutting down.");
				callback.run();
			}

			@Override
			public void start() {
				log.info("GraceFul::Quartz Graceful Shutdown Hook started.");
				isRunning = true;
			}

			@Override
			public void stop() {
				isRunning = false;
				try {
					log.info("GraceFul::Quartz Graceful Shutdown... ");
					schedulerFactoryBean.destroy();
				} catch (SchedulerException e) {
					try {
						log.info(
								"Error shutting down Quartz: " + e.getMessage(), e);
						schedulerFactoryBean.getScheduler().shutdown(false);
					} catch (SchedulerException ex) {
						log.error("Unable to shutdown the Quartz scheduler.", ex);
					}
				}
			}

			@Override
			public boolean isRunning() {
				log.info("GraceFul::isRunning");
				return isRunning;
			}

			@Override
			public int getPhase() {
				log.info("GraceFul::getPhase");

				return Integer.MAX_VALUE;
			}
		};
	}


}
