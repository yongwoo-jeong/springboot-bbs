package com.example.ebrainstudy__springbootbbs;

import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class EbrainStudySpringbootBbsApplication {

	@GetMapping("/")
	public String home(@RequestParam String id, HttpServletRequest req){
		req.setAttribute("id",id);
		return "index";
	}

	public static void main(String[] args) {
		SpringApplication.run(EbrainStudySpringbootBbsApplication.class, args);
	}

}

