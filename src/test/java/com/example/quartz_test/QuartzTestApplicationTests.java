package com.example.quartz_test;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class QuartzTestApplicationTests {

	@Test
	public void testEncrypt() {

		StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
		pbeEnc.setAlgorithm("PBEWithMD5AndDES");
		pbeEnc.setPassword("test"); //2번 설정의 암호화 키를 입력

		String enc = pbeEnc.encrypt("roqkf12#"); //암호화 할 내용
		System.out.println("enc = " + enc); //암호화 한 내용을 출력

		//테스트용 복호화
		String des = pbeEnc.decrypt(enc);
		System.out.println("des = " + des);
	}
}
