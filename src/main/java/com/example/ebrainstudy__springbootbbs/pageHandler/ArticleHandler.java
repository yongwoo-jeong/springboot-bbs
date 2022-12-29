package com.example.ebrainstudy__springbootbbs.pageHandler;

import com.example.ebrainstudy__springbootbbs.article.ArticleDAO;
import com.example.ebrainstudy__springbootbbs.article.ArticleVO;
import com.example.ebrainstudy__springbootbbs.searchCondition.SearchCondition;
import com.example.ebrainstudy__springbootbbs.searchCondition.SearchConditionVO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArticleHandler implements PageHandlerInterface {
	private final ArticleDAO articleDAO;
	private final int targetArticleId;
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
