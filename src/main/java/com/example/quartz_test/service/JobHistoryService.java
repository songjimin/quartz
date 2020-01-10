package com.example.quartz_test.service;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import com.example.quartz_test.model.JobHistory;
import com.example.quartz_test.repository.JobHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JobHistoryService {

	@Autowired
	private JobHistoryRepository jobHistoryRepository;

	public void saveTriggerHistoryInfo(String status, JobKey jobKey, Trigger trigger) {

		JobHistory jobHistory = JobHistory.builder()
				.listener("Trigger")
				.status(status)
				.serverIp(getServerIp())
				.gameCode(jobKey.getGroup())
				.targetId(jobKey.getName())
				.targetServerInfo(String.valueOf(trigger.getJobDataMap().get("targetServerAddress")) + ":" + String.valueOf(trigger.getJobDataMap().get("targetServerPort")))
				.interval(String.valueOf(trigger.getJobDataMap().get("interval")))
				.previousFireTime(trigger.getPreviousFireTime())
				.nextFireTime(trigger.getNextFireTime())
				.build();

		log.info("JobHistory Log : {}", jobHistory.toString());
		jobHistoryRepository.save(jobHistory);
	}


	//현재 시스템의 모든 네트워크 인터페이스를 읽어와서 loopback장치인지 랜선에 연결된 장치인지 여부를 확인하여 실제 사용중인 인터페이스의 IP주소를 읽어오게 됩니다.
	//http://theeye.pe.kr/archives/1501
	private String getServerIp() {
		try
		{
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();)
			{
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
				{
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress())
					{
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		}
		catch (SocketException ex) {}
		return null;
	}
}
