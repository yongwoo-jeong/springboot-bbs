package com.springboot.bbs.controller;

import com.springboot.bbs.service.ArticleService;
import com.springboot.bbs.vo.ArticleVO;
import com.springboot.bbs.vo.SearchCriteriaVO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class ArticleController {
	private final ArticleService articleService;
	@GetMapping("/")
	public String homeController(Model model, @ModelAttribute SearchCriteriaVO searchCriteria){
		List<ArticleVO> articles = articleService.homeService(0);
		model.addAttribute("articles",articles);
		model.addAttribute("SearchCriteria",searchCriteria);
		return "index";
	}
}
