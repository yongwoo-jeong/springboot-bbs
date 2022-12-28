package com.example.ebrainstudy__springbootbbs.pageHandler;

import com.example.ebrainstudy__springbootbbs.article.ArticleDAO;
import com.example.ebrainstudy__springbootbbs.article.ArticleVO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InputArticlePageHandler implements PageHandlerInterface {
	private ArticleDAO articleDAO;
	private ArticleVO insertingArticle;
	public InputArticlePageHandler(ArticleDAO articleDAO){
		this.articleDAO = articleDAO;
	}

	public void setInsertingArticle(ArticleVO insertingArticle) {
		this.insertingArticle = insertingArticle;
	}

	@Override
	public void process(HttpServletRequest req, HttpServletResponse res) throws ServletException {
	}
}
