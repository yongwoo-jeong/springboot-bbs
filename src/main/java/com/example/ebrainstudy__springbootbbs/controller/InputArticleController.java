package com.example.ebrainstudy__springbootbbs.controller;

import com.example.ebrainstudy__springbootbbs.article.ArticleVO;
import com.example.ebrainstudy__springbootbbs.exception.InputFIeldException;
import com.example.ebrainstudy__springbootbbs.searchCondition.SearchConditionVO;
import com.example.ebrainstudy__springbootbbs.service.InsertArticleService;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * /upload 로 요청되는 GET,POST 컨트롤러
 */
@Controller
public class InputArticleController {

	/**
	 * 서비스 컴포넌트 DI
	 */
	private final InsertArticleService inputService;
	@Autowired
	public InputArticleController(InsertArticleService inputService){
		this.inputService = inputService;
	}
	/**
	 * /upload 로 들어오는 GET 요청을
	 * 새게시글 등록 페이지(newArticleInput.jsp)로 리턴
	 * @return newArticleInput.jsp
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
								@RequestParam(value = "files",required = false) List<MultipartFile> multipartFileList,
								SearchConditionVO searchCondition){
		inputService.verifyAndSetArticle(newArticle);
		// 파일 리스트가 비어있지 않을경우 setFileList setFileList 호출
		if (!multipartFileList.isEmpty()){ inputService.setFileList(multipartFileList);}
		inputService.process(req, res, searchCondition);

	}
}
