package com.example.quartz_test.service;

import java.util.Calendar;

import org.springframework.stereotype.Service;

@Service
public class TimePrintService {

	public void printTime() {
		Calendar date = Calendar.getInstance();
		String stamp = date.get(Calendar.HOUR_OF_DAY)+":"
				+date.get(Calendar.MINUTE)+":"
				+date.get(Calendar.SECOND)+":"
				+date.get(Calendar.MILLISECOND);
		System.out.println(stamp + " ");
	}

}
