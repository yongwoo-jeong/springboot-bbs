package com.springboot.bbs.controller;

import com.springboot.bbs.service.ArticleService;
import com.springboot.bbs.vo.ArticleVO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ArticleController {
	private final ArticleService articleService;
	@GetMapping("/")
	public String homeController(Model model){
		List<ArticleVO> articles = articleService.homeService(0);
		model.addAttribute("articles",articles);
		return "index";
	}
}
