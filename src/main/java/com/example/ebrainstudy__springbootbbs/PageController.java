package com.example.ebrainstudy__springbootbbs;

import com.example.ebrainstudy__springbootbbs.article.ArticleDAO;
import com.example.ebrainstudy__springbootbbs.article.ArticleVO;
import com.example.ebrainstudy__springbootbbs.logger.MyLogger;
import com.example.ebrainstudy__springbootbbs.pageHandler.IndexPageHandler;
import com.example.ebrainstudy__springbootbbs.pageHandler.InputArticlePageHandler;
import com.example.ebrainstudy__springbootbbs.searchCondition.SearchConditionVO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
			IndexPageHandler indexPageHandler = new IndexPageHandler(new ArticleDAO());
			indexPageHandler.setSearchCondition(searchConditionParameter);
			indexPageHandler.process(req, res);
		} catch (RuntimeException e) {
			logger.severe(className+"homeController RuntimeException occurred");
			logger.severe(String.valueOf(e));
		}
		return "index";
	}
	/**
	 * /upload 로 들어오는 GET 요청을
	 * 새게시글 등록 페이지로 리턴
	 * @return
	 */
	@GetMapping("/upload")
	public String inputNewArticleController(){
		return "newArticleInputForm";
	}
	/**
	 *
	 * @param req
	 * @param res
	 * @param createdArticle
	 * @throws ServletException
	 */
	@PostMapping("/upload")
	public void postNewArticle(HttpServletRequest req, HttpServletResponse res,@ModelAttribute ArticleVO createdArticle) throws ServletException {
		try{
			InputArticlePageHandler inputHandler = new InputArticlePageHandler(new ArticleDAO());
			inputHandler.setInsertingArticle(createdArticle);
			inputHandler.process(req, res);
		} catch (ServletException e) {
			logger.severe(className+"homeController Exception");
			logger.severe(String.valueOf(e));
		}

	}
}
