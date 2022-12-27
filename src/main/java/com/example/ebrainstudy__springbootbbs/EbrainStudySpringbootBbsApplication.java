package com.example.ebrainstudy__springbootbbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Controller;

@Controller
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class EbrainStudySpringbootBbsApplication {
	public static void main(String[] args) {
		SpringApplication.run(EbrainStudySpringbootBbsApplication.class, args);
	}
}

