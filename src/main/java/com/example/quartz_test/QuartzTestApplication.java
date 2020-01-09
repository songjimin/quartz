package com.example.quartz_test;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableEncryptableProperties
@SpringBootApplication
public class QuartzTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuartzTestApplication.class, args);
	}

}
