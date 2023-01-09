package com.springboot.bbs.controller;

import com.springboot.bbs.service.ArticleService;
import com.springboot.bbs.service.FileService;
import com.springboot.bbs.utils.StringUtils;
import com.springboot.bbs.vo.ArticleVO;
import com.springboot.bbs.vo.CategoryVO;
import com.springboot.bbs.vo.CommentVO;
import com.springboot.bbs.vo.FileVO;
import com.springboot.bbs.vo.SearchCriteriaVO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
	 * 파일 서비스 객체
	 */
	private final FileService fileService;

	@Value("${dev.file.localPath}")
	private String serverFilePath;

	/**
	 * 홈페이지(/) GET 매핑
	 * @param model 애트리뷰트 설정을 위한 모델객체
	 * @param searchCriteria 검색조건, 조건이 없을 경우 아티클매퍼에서 trim 처리로 조건 제외
	 * @return
	 */
	@GetMapping("/")
	public String homeController(Model model, @ModelAttribute SearchCriteriaVO searchCriteria){
		// 카테고리명
		List<CategoryVO> categories = articleService.selectCategories();
		model.addAttribute("categories",categories);
		// DB SELECT LIMIT offset 설정
		searchCriteria.setDbLimitOffset((searchCriteria.getCurrentPage()-1)*10);
		// 게시글리스트, 게시글 숫자 담은 DTO
		List<ArticleVO> searchedArticles = articleService.selectArticleList(searchCriteria);
		int countArticles =  articleService.selectSearchedArticleCount(searchCriteria);
		// 게시글 리스트 애트리뷰트
		model.addAttribute("articles",searchedArticles);
		model.addAttribute("articlesCount",countArticles);
		// 검색조건 쿼리스트링 파라미터 애트리뷰트
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
	@GetMapping("/article")
	public String articleDetailController(HttpServletRequest req,Model model, @RequestParam("id") Integer articleId, @ModelAttribute SearchCriteriaVO searchCriteria){
		// 요청 게시글 정보 가져오기
		ArticleVO targetArticle = articleService.selectArticleDetail(articleId);
		// 게시글에 따른 댓글 리스트
		List<CommentVO> commentList = articleService.selectCommentList(articleId);
		// 게시글에 따른 파일 리스트
		List< FileVO> fileList = fileService.getFileList(articleId);
		// 검색조건유지를 위한 쿼리스트링 설정
		model.addAttribute("targetArticle", targetArticle);
		model.addAttribute("commentList", commentList);
		model.addAttribute("fileList", fileList);
		return "articleDetail";
	}

	@PostMapping("/addComment")
	public String addComment(HttpServletRequest req, @RequestParam("id") Integer articleId,@ModelAttribute CommentVO newComment){
		articleService.insertNewComment(articleId,newComment);
		// 댓글 POST 요청 후 이전 페이지로 돌리기 위한 referer
		return "redirect:/article?"+req.getQueryString();
	}

	/**
	 * 게시글 등록(/upload) 페이지 GET 매핑
	 * @return 새 게시글 등록 FOAM 화면
	 */
	@GetMapping("/insertArticle")
	public String inputArticleController(Model model){
		// 카테고리명
		List<CategoryVO> categories = articleService.selectCategories();
		model.addAttribute("categories",categories);
		return "articleInput";
	}

	/**
	 * 폼을 통해 받은 새 게시글 정보 POST 요청 처리
	 * TODO 에러코드 받아서 enum 으로 처리
	 * @return
	 */
	@PostMapping("/insertArticle")
	public String insertArticleController(HttpServletRequest req,
										  @ModelAttribute ArticleVO newArticle,
										  @RequestParam("passwordConfirm") String passwordConfirm,
										  @RequestParam(value = "files",required = false) List<MultipartFile> multipartFileList,
										  @ModelAttribute SearchCriteriaVO searchCriteria){
		// 서비스컴포넌트에서 항목 검증 시도
		int insertedArticleId = articleService.insertNewArticle(newArticle, passwordConfirm);
		System.out.println(insertedArticleId);

		// 실패시 에러페이지
		if (insertedArticleId < 0 ){
			System.out.println(insertedArticleId);
			return "insertError";
		}
		// 파일 처리 서비스
		fileService.insertNewFiles(multipartFileList, insertedArticleId);
		// 게시글 등록후는 현재페이지 1로 설정
		String searchQueryString = req.getQueryString();
		return "redirect:/?"+searchQueryString;
	}

	/**
	 * 게시글 삭제 컨트롤러
	 * @param articleId 게시글 id
	 * @param userInputPassword 유저 입력 비밀번호
	 * @param searchCriteria 검색조건 객체
	 */
	@PostMapping ("/deleteArticle")
	public String deleteArticleController(HttpServletRequest req,
									      @RequestParam("id") Integer articleId,
										  @RequestParam("password") String userInputPassword,
										  @ModelAttribute SearchCriteriaVO searchCriteria){
		Boolean isPasswordCorrect = articleService.isPasswordConfirmed(userInputPassword,articleId);
		if (!isPasswordCorrect){
			return "error";
		}
		articleService.deleteArticle(articleId);
		// 검색조건유지를 위한 쿼리스트링파라미터
		String queryString = StringUtils.deleteArticleId(req.getQueryString());
		return "redirect:/?"+queryString;
	}

	/**
	 * 게시글 수정 페이지 컨트롤러
	 * @param articleId 게시글 id
	 * @param userInputPassword 유저 입력 비밀번호
	 * @param searchCriteria 검색조건 객체
	 * @return
	 */
	@PostMapping("/update")
	public String editArticleController(Model model,
										@RequestParam("id") Integer articleId,
										@RequestParam("password") String userInputPassword,
										@ModelAttribute SearchCriteriaVO searchCriteria){
		Boolean isPasswordCorrect = articleService.isPasswordConfirmed(userInputPassword,articleId);
		if (!isPasswordCorrect){
			return "error";
		}
		ArticleVO article = articleService.selectArticleDetail(articleId);
		List<FileVO> fileList = fileService.getFileList(articleId);
		// 카테고리명
		List<CategoryVO> categories = articleService.selectCategories();
		model.addAttribute("categories",categories);
		model.addAttribute("article", article);
		model.addAttribute("fileList",fileList);
		model.addAttribute("searchCriteria", searchCriteria);
		return "articleEdit";
	}

	/**
	 * 게시글 수정 요청 POST 요청 컨트롤러
	 * @param userUpdatedArticle 수정된 게시글 객체
	 * @param multipartFileList 수정된 파일 리스트
	 * @param searchCriteria 검색조건
	 * @return
	 */
	@PostMapping("/updateAction")
	public String onEditController(HttpServletRequest req,
								   @ModelAttribute ArticleVO userUpdatedArticle ,
								   @ModelAttribute SearchCriteriaVO searchCriteria,
								   @RequestParam("id") Integer articleId,
								   @RequestParam String deleteFileList,
								   @RequestParam(value = "files",required = false) List<MultipartFile> multipartFileList){
		String dbPassword = articleService.selectArticleDetail(articleId).getPassword();
		if (!dbPassword.equals(userUpdatedArticle.getPassword())){
			return "error";
		}
		articleService.updateArticle(userUpdatedArticle, articleId);
		String[] fileWillDelete = deleteFileList.split(",");
		for (String fileUuid : fileWillDelete){
			fileService.deleteFile(fileUuid);
		}
		fileService.insertNewFiles(multipartFileList, articleId);
		String queryString = req.getQueryString();
		return "redirect:/article?"+queryString;

	}
}
