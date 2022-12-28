package com.example.ebrainstudy__springbootbbs.pageHandler;

import com.example.ebrainstudy__springbootbbs.article.ArticleDAO;
import com.example.ebrainstudy__springbootbbs.article.ArticleVO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public class InputArticlePageHandler implements PageHandlerInterface {

	/**
	 * 게시글을 INSERT 하기 위해 DAO 객체 주입
	 */
	private ArticleDAO articleDAO;
	/**
	 * INSERT 될 새 게시글
	 */
	private ArticleVO insertingArticle;
	public InputArticlePageHandler(ArticleDAO articleDAO){
		this.articleDAO = articleDAO;
	}
	public void setInsertingArticle(ArticleVO insertingArticle) {
		this.insertingArticle = insertingArticle;
	}
	@Override
	public void process(HttpServletRequest req, HttpServletResponse res) throws ServletException {
		articleDAO.insertNewArticle(insertingArticle);
	}
}
