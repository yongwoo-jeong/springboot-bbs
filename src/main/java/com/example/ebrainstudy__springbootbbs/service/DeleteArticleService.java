package com.example.ebrainstudy__springbootbbs.service;

import com.example.ebrainstudy__springbootbbs.article.ArticleDAO;
import com.example.ebrainstudy__springbootbbs.comment.CommentDAO;
import com.example.ebrainstudy__springbootbbs.file.FileDAO;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteArticleService {
	private final ArticleDAO articleDAO;
	private final FileDAO fileDAO;
	private final CommentDAO commentDAO;
	private Integer articleId;

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public void process(HttpServletRequest req, HttpServletResponse res){
		commentDAO.deleteCommentOnArticle(articleId);
		fileDAO.deleteFileOnArticle(articleId);
		articleDAO.deleteArticle(articleId);
		try {
			res.sendRedirect("/");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}




}
