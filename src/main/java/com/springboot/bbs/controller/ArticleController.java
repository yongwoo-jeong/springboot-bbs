package com.springboot.bbs.controller;

import com.springboot.bbs.dto.ArticleDTO;
import com.springboot.bbs.service.ArticleService;
import com.springboot.bbs.utils.StringUtils;
import com.springboot.bbs.vo.SearchCriteriaVO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
		// DB SELECT LIMIT offset 설정
		searchCriteria.setDbLimitOffset((searchCriteria.getCurrentPage()-1)*10);
		// 게시글리스트, 게시글 숫자 담은 DTO
		ArticleDTO articleDTO = articleService.homeService(searchCriteria);
		// 게시글 리스트
		model.addAttribute("articles",articleDTO.getSearchedArticles());
		// 게시글 숫자
		model.addAttribute("articlesCount",articleDTO.getSearchedArticlesCount());
		// 검색조건 쿼리스트링 파라미터
		String queryStringParam = StringUtils.makeQueryString(searchCriteria);
		model.addAttribute("queryStringParam",queryStringParam);
		// 현재페이지는 조건에 들어가지 않기때문에 따로 넘겨준다.
		model.addAttribute("currentPage", searchCriteria.getCurrentPage());
		return "home";
	}

	/**
	 * 개별 게시글(/article) 페이지 GET 매핑
	 * @param model 애트리뷰트 설정을 위한 모델객체
	 * @param articleId 목적이 되는 게시글 ID 를 쿼리스트링 파라미터로 받아온다
	 * @return
	 */
//	@GetMapping("/article")
//	public String articleDetailController(Model model, @RequestParam("id") Integer articleId, @ModelAttribute SearchCriteriaVO searchCriteria){
////		ArticleVO targetArticle = articleService.articleDetailService(articleId);
//		String queryStringParam = StringUtils.makeQueryString(searchCriteria);
//		model.addAttribute("targetArticle", targetArticle);
//		model.addAttribute("queryStringParam",queryStringParam);
//		model.addAttribute("currentPage",searchCriteria.getCurrentPage());
//		return "articleDetail";
//	}

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
	public String insertArticleController(@ModelAttribute ArticleDTO newArticle , @RequestParam(value = "files",required = false) List<MultipartFile> multipartFileList){
		// 서비스컴포넌트에서 항목 검증 시도
		int insertStatus = articleService.insertNewArticle(newArticle);
		// 실패시 에러페이지
		if (insertStatus == -1 ){
			return "insertError";
		}
		return "redirect:/";
	}


}
