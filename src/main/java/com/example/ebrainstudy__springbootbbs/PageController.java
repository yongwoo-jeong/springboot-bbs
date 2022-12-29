package com.example.ebrainstudy__springbootbbs;

import com.example.ebrainstudy__springbootbbs.article.ArticleDAO;
import com.example.ebrainstudy__springbootbbs.article.ArticleVO;
import com.example.ebrainstudy__springbootbbs.logger.MyLogger;
import com.example.ebrainstudy__springbootbbs.pageHandler.ArticleViewHandler;
import com.example.ebrainstudy__springbootbbs.pageHandler.IndexHandler;
import com.example.ebrainstudy__springbootbbs.pageHandler.InputArticleHandler;
import com.example.ebrainstudy__springbootbbs.searchCondition.SearchConditionVO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 전체 페이지 요청을 핸들러로 보내 처리하는 컨트롤러
 */
@Controller
public class PageController {

	/**
	 * 로깅을 위한 마이로거 인스턴스 획득
	 */
	MyLogger logger = MyLogger.getLogger();
	/**
	 * 로깅을 위한 현재 클래스 네임 획득
	 */
	String className = MyLogger.getClassName();

	/**
	 * Index 페이지("/") 컨트롤러
	 * IndexPageHandler 를 통해 검색 조건을 설정하고
	 * 조건에 맞는 Articles 를 애트리뷰트 형태로 프론트에 넘겨줌
	 * @param req
	 * @param res
	 * @param searchConditionParameter 쿼리스트링으로 만들어진 검색조건 SearchConditionVO
	 * @return index.jsp 로 리턴
	 */
	@RequestMapping("/")
	public String  homeController(HttpServletRequest req, HttpServletResponse res, SearchConditionVO searchConditionParameter){
		// DAO 가 static 으로 선언되어야하나?
		try {
			IndexHandler indexPageHandler = new IndexHandler(new ArticleDAO());
			indexPageHandler.setSearchCondition(searchConditionParameter);
			indexPageHandler.process(req, res);
		} catch (RuntimeException e) {
			logger.severe(className+"homeController RuntimeException occurred");
			logger.severe(String.valueOf(e));
		}
		return "index";
	}
	@GetMapping("/article")
	public void articleViewController(HttpServletRequest req, HttpServletResponse res, @RequestParam int id){
		ArticleViewHandler articleViewHandler = new ArticleViewHandler(new ArticleDAO(), id);

//		return "articleView";
	}
	/**
	 * /upload 로 들어오는 GET 요청을
	 * 새게시글 등록 페이지로 리턴
	 * @return
	 */
	@GetMapping("/upload")
	public String inputNewArticleController(){
		return "newArticleInput";
	}
	/**
	 * /upload 로 들어오는 POST 요청을 처리
	 * @param req
	 * @param res
	 * @param newArticle newArticleInput 에서 만들어진 Article 객체
	 * @throws ServletException
	 */
	@PostMapping("/upload")
	public void postNewArticle(HttpServletRequest req, HttpServletResponse res, @ModelAttribute ArticleVO newArticle,
								@RequestParam(value = "files",required = false) List<MultipartFile> multipartFileList){
		try {
			InputArticleHandler inputHandler = new InputArticleHandler(new ArticleDAO());
			inputHandler.setInsertingArticle(newArticle);
			inputHandler.process(req, res);
			String uploadDir = "/Users/jyw/Desktop/project/java/ebrain-study__model2-bbs/apache-tomcat-9.0.10/file/";
			for (MultipartFile file : multipartFileList) {
				if (file.getSize() == 0) {
					continue;
				} else {
					System.out.println(file.getOriginalFilename());
				}
			}
		}
		 catch (IOException e) {
			logger.severe(className+"homeController Exception");
			logger.severe(String.valueOf(e));
		}
	}
}
