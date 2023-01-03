package com.springboot.bbs.controller;

import com.springboot.bbs.dto.ArticleDTO;
import com.springboot.bbs.service.ArticleService;
import com.springboot.bbs.utils.StringUtils;
import com.springboot.bbs.vo.ArticleVO;
import com.springboot.bbs.vo.SearchCriteriaVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 게시글 관련 요청 컨트롤러
 */
@Controller
@RequiredArgsConstructor
public class ArticleController {

	/**
	 * 게시글 관련 서비스 객체
	 */
	private final ArticleService articleService;

	/**
	 * 홈페이지(/) GET 매핑
	 * @param model 애트리뷰트 설정을 위한 모델객체
	 * @param searchCriteria 검색조건, 조건이 없을 경우 아티클매퍼에서 trim 처리로 조건 제외
	 * @return
	 */
	@GetMapping("/")
	public String homeController(Model model, @ModelAttribute SearchCriteriaVO searchCriteria){
		/**
		 * 조건에 따른 검색결과
		 */
		searchCriteria.setDbLimitOffset((searchCriteria.getCurrentPage()-1)*10);
		ArticleDTO articleDTO = articleService.homeService(searchCriteria);
		String queryStringParam = StringUtils.makeQueryString(searchCriteria);
		model.addAttribute("articles",articleDTO.getSearchedArticles());
		model.addAttribute("articlesCount",articleDTO.getSearchedArticlesCount());
		model.addAttribute("SearchCriteria",searchCriteria);
		model.addAttribute("queryStringParam",queryStringParam);
		model.addAttribute("currentPage", searchCriteria.getCurrentPage());
		return "home";
	}

	/**
	 * 개별 게시글(/article) 페이지 GET 매핑
	 * @param model 애트리뷰트 설정을 위한 모델객체
	 * @param articleId 목적이 되는 게시글 ID 를 쿼리스트링 파라미터로 받아온다
	 * @return
	 */
	@GetMapping("/article")
	public String articleDetailController(Model model, @RequestParam("id") Integer articleId){
		ArticleVO targetArticle = articleService.articleDetailService(articleId);
		model.addAttribute("targetArticle", targetArticle);
		return "articleDetail";
	}

	/**
	 * 게시글 등록(/upload) 페이지 GET 매핑
	 * @return 새 게시글 등록 FOAM 화면
	 */
	@GetMapping("/upload")
	public String inputArticleController(){
		return "articleInput";
	}

	/**
	 * 폼을 통해 받은 새 게시글 정보 POST 요청 처리
	 * @return
	 */
	@PostMapping("/upload")
	public String insertArticleController(@ModelAttribute ArticleVO newArticle){
		return "";
	}


}
