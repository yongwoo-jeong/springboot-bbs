package com.example.ebrainstudy__springbootbbs.controller;

import com.example.ebrainstudy__springbootbbs.searchCondition.SearchConditionVO;
import com.example.ebrainstudy__springbootbbs.service.IndexService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 전체 페이지 요청을 핸들러로 보내 처리하는 컨트롤러
 */
@Controller
public class IndexController {
	/**
	 * 인덱스페이지("/") 서비스
	 */
	private final IndexService indexPageService;
	@Autowired
	public IndexController(IndexService indexPageService){
		this.indexPageService = indexPageService;
	}
	/**
	 * Index 페이지("/") 컨트롤러
	 * IndexPageHandler 를 통해
	 * 검색 조건에 맞는 Article List를 프론트에 넘겨줌
	 * @param req
	 * @param res
	 * @param searchConditionParameter 쿼리스트링으로 만들어진 검색조건 SearchConditionVO
	 * @return index.jsp
	 */
	@RequestMapping("/")
	public String  homeController(HttpServletRequest req, HttpServletResponse res, SearchConditionVO searchConditionParameter){
		indexPageService.process(req, res, searchConditionParameter);
		return "index";
	}
}
