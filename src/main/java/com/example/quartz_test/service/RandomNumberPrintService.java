package com.example.quartz_test.service;

import org.springframework.stereotype.Service;

@Service
public class RandomNumberPrintService {

	public void printRandomNumber() {
		System.out.println(Math.random());
	}
}
