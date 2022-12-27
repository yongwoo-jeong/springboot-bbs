package com.example.ebrainstudy__springbootbbs;

import article.ArticleDAO;
import article.ArticleVO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class EbrainStudySpringbootBbsApplication {
	@RequestMapping("/")
	public String HandleHome(HttpServletRequest req){
		List<ArticleVO> searchedArticles = new ArticleDAO().searchArticles();
		req.setAttribute("articles", searchedArticles);
		req.setAttribute("id", "this is ID");
		return "index";
	}
	public static void main(String[] args) {
		SpringApplication.run(EbrainStudySpringbootBbsApplication.class, args);
	}

}

