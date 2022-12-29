package com.example.ebrainstudy__springbootbbs.controller;

import com.example.ebrainstudy__springbootbbs.logger.MyLogger;
import com.example.ebrainstudy__springbootbbs.searchCondition.SearchConditionVO;
import com.example.ebrainstudy__springbootbbs.service.IndexPageService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 전체 페이지 요청을 핸들러로 보내 처리하는 컨트롤러
 */
@Controller
public class IndexPageController {
	/**
	 * 로깅을 위한 마이로거 인스턴스 획득
	 */
	MyLogger logger = MyLogger.getLogger();
	/**
	 * 로깅을 위한 현재 클래스 네임 획득
	 */
	String className = MyLogger.getClassName();
	/**
	 * 서비스 의존성 주입
	 */
	private IndexPageService indexPageService;
	@Autowired
	public void setIndexPageHandler(IndexPageService indexPageService){
		this.indexPageService = indexPageService;
	}
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
		try {
			indexPageService.process(req, res, searchConditionParameter);
		} catch (RuntimeException e) {
			logger.severe(className+"homeController RuntimeException occurred");
			logger.severe(String.valueOf(e));
		}
		return "index";
	}
}
