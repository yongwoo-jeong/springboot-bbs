package com.example.ebrainstudy__springbootbbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class EbrainStudySpringbootBbsApplication {
	public static void main(String[] args) {
		SpringApplication.run(EbrainStudySpringbootBbsApplication.class, args);

	}
}

