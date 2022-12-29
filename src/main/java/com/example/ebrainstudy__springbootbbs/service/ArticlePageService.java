package com.example.ebrainstudy__springbootbbs.service;

import com.example.ebrainstudy__springbootbbs.article.ArticleDAO;
import com.example.ebrainstudy__springbootbbs.article.ArticleVO;
import com.example.ebrainstudy__springbootbbs.searchCondition.SearchCondition;
import com.example.ebrainstudy__springbootbbs.searchCondition.SearchConditionVO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
public class ArticlePageService implements ServiceInterface {
	private final ArticleDAO articleDAO;
	private final Integer targetArticleId;
	private ArticleVO targetArticle;
	public void setTargetArticle(){
		this.targetArticle = articleDAO.getArticle(targetArticleId);
	}
	@Override
	public void process(HttpServletRequest req, HttpServletResponse res, SearchConditionVO searchCondition){
		if (searchCondition!=null){
			String searchQueryString = new SearchCondition().makeQuerystring(searchCondition);
			System.out.println(searchQueryString+searchCondition.getCurrentPage());
			req.setAttribute("searchQueryString",searchQueryString+searchCondition.getCurrentPage());
		}
		req.setAttribute("article", targetArticle);
	}
}
