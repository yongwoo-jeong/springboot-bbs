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
/**
 * 게시글 관련 요청 컨트롤러
 */
public class ArticleController {

	/**
	 * 서비스 객체
	 */
	private final ArticleService articleService;

	@GetMapping("/")
	public String homeController(Model model, @ModelAttribute SearchCriteriaVO searchCriteria){
		/**
		 * 조건에 따른 검색결과
		 */
		List<ArticleVO> articles = articleService.homeService(0,searchCriteria);
		model.addAttribute("articles",articles);
		model.addAttribute("SearchCriteria",searchCriteria);
		return "index";
	}
}
