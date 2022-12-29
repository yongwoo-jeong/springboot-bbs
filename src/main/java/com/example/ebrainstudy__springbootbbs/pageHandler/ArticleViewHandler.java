package com.example.ebrainstudy__springbootbbs.pageHandler;

import com.example.ebrainstudy__springbootbbs.article.ArticleDAO;
import com.example.ebrainstudy__springbootbbs.article.ArticleVO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArticleViewHandler implements PageHandlerInterface {
	private final ArticleDAO articleDAO;
	private final int targetArticleId;
	private ArticleVO targetArticle;
	public void setTargetArticle(){
		this.targetArticle = articleDAO.getArticle(targetArticleId);
	}
	@Override
	public void process(HttpServletRequest req, HttpServletResponse res){
		req.setAttribute("article", targetArticle);
	}
}
