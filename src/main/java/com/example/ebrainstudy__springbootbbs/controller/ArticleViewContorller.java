package com.example.ebrainstudy__springbootbbs.controller;

import com.example.ebrainstudy__springbootbbs.searchCondition.SearchConditionVO;
import com.example.ebrainstudy__springbootbbs.service.ArticleViewService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArticleViewContorller {
	private ArticleViewService articleViewService;
	@Autowired
	public void setArticleViewService(ArticleViewService articleViewService) {
		this.articleViewService = articleViewService;
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
	public String articleViewController(HttpServletRequest req, HttpServletResponse res, @RequestParam int id, SearchConditionVO searchConditionParameter){
		articleViewService.setTargetArticleId(id);
		articleViewService.process(req,res, searchConditionParameter);
		return "articleView";
	}
}
