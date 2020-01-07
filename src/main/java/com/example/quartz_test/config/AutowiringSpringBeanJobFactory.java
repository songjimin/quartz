package com.example.quartz_test.config;

import org.quartz.spi.TriggerFiredBundle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

public class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {

	private transient AutowireCapableBeanFactory beanFactory;

	//ApplicationContextAware? https://javaslave.tistory.com/50
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		//AutowireCapableBeanFactory : 의존 관계를 주입하면서 bean을 만들어 주는 AutowireCapableBeanFactory가 있다
		beanFactory = applicationContext.getAutowireCapableBeanFactory();
	}

	//Injection
	@Override
	protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
		final Object job = super.createJobInstance(bundle);
		beanFactory.autowireBean(job);
		return job;
	}

}
