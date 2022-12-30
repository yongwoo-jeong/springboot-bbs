package com.example.ebrainstudy__springbootbbs.service;

import com.example.ebrainstudy__springbootbbs.article.ArticleDAO;
import com.example.ebrainstudy__springbootbbs.article.ArticleVO;
import com.example.ebrainstudy__springbootbbs.searchCondition.SearchConditionVO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleViewService implements ServiceInterface {
	private final ArticleDAO articleDAO;
	private Integer targetArticleId;
	public void setTargetArticleId(Integer targetArticleId) {
		this.targetArticleId = targetArticleId;
	}
	@Override
	public void process(HttpServletRequest req, HttpServletResponse res, SearchConditionVO searchCondition){
		ArticleVO targetArticle = articleDAO.getArticle(targetArticleId);
		req.setAttribute("article", targetArticle);
	}
}
