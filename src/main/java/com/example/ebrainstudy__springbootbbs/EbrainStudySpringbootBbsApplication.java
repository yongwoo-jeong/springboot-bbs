package com.example.ebrainstudy__springbootbbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class EbrainStudySpringbootBbsApplication {

	@GetMapping("/")
	public String home(){
		return "index";
	}

	public static void main(String[] args) {
		SpringApplication.run(EbrainStudySpringbootBbsApplication.class, args);
	}

}

