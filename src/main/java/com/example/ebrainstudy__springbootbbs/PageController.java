package com.example.ebrainstudy__springbootbbs;

import com.example.ebrainstudy__springbootbbs.article.ArticleDAO;
import com.example.ebrainstudy__springbootbbs.article.ArticleVO;
import com.example.ebrainstudy__springbootbbs.exception.InputFIeldException;
import com.example.ebrainstudy__springbootbbs.logger.MyLogger;
import com.example.ebrainstudy__springbootbbs.pageHandler.ArticleHandler;
import com.example.ebrainstudy__springbootbbs.pageHandler.IndexHandler;
import com.example.ebrainstudy__springbootbbs.pageHandler.InputArticleHandler;
import com.example.ebrainstudy__springbootbbs.searchCondition.SearchConditionVO;
import java.io.IOException;
import java.util.List;
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
	 * 조건에 맞는 Article List 를 애트리뷰트로 프론트에 넘겨줌
	 * @param req
	 * @param res
	 * @param searchConditionParameter 쿼리스트링으로 만들어진 검색조건 SearchConditionVO
	 * @return index.jsp
	 */
	@RequestMapping("/")
	public String  homeController(HttpServletRequest req, HttpServletResponse res, SearchConditionVO searchConditionParameter){
		// DAO 가 static 으로 선언될수있나?
		try {
			IndexHandler indexPageHandler = new IndexHandler(new ArticleDAO());
			indexPageHandler.process(req, res);
		} catch (RuntimeException e) {
			logger.severe(className+"homeController RuntimeException occurred");
			logger.severe(String.valueOf(e));
		}
		return "index";
	}
	/**
	 * /article get 요청을 핸들러를 통해 게시글 정보를 받아와
	 * 애트리뷰트로 뷰에 넘겨줌
	 * @param req
	 * @param res
	 * @param id 쿼리스트링으로 요청된 게시글 id
	 * @return articleView.jsp
	 */
	@GetMapping("/article")
	public String articleViewController(HttpServletRequest req, HttpServletResponse res, @RequestParam int id){
		ArticleHandler articleViewHandler = new ArticleHandler(new ArticleDAO(), id);
		articleViewHandler.setTargetArticle();
		articleViewHandler.process(req,res);
		return "articleView";
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
	 * /upload post 요청 처리
	 * 게시글 정보, 파일 리스트를 받아서 컨트롤러 통해 DB에 INSERT
	 * @param req
	 * @param res
	 * @param newArticle
	 * @param multipartFileList
	 * @throws IOException 게시글을 DB에 INSERT 할때 가능한 예외
	 * @throws InputFIeldException 게시글 업로드 INPUT 입력 조건 검증에 실패했을 경우 발생하는 런타임 예외
	 */
	@PostMapping("/upload")
	public void postNewArticle(HttpServletRequest req, HttpServletResponse res,
								@ModelAttribute ArticleVO newArticle,
								@RequestParam(value = "files",required = false) List<MultipartFile> multipartFileList){
		try {
			InputArticleHandler inputHandler = new InputArticleHandler(new ArticleDAO());
			inputHandler.setInsertingArticle(newArticle);
			// 파일 리스트가 비어있지 않을경우 setFileList setFileList 호출
			if (!multipartFileList.isEmpty()){
				inputHandler.setFileList(multipartFileList);
			}
			inputHandler.process(req, res);
		}
		 catch (IOException | InputFIeldException e) {
			logger.severe(className+"homeController Exception");
			logger.severe(String.valueOf(e));
		}
	}
}
