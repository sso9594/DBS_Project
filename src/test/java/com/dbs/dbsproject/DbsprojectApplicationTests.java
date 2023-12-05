package com.dbs.dbsproject;

import com.dbs.dbsproject.util.PasswordEncoder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;

@SpringBootTest
class DbsprojectApplicationTests {

	private final PasswordEncoder encoder;

	DbsprojectApplicationTests(@Autowired PasswordEncoder encoder) {
		this.encoder = encoder;
	}

	@Test
	void contextLoads() throws NoSuchAlgorithmException {
		String asd = "asdasd";
		System.out.println(encoder.encode(asd));
	}

}
