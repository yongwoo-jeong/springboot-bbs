package com.example.ebrainstudy__springbootbbs.service;

import com.example.ebrainstudy__springbootbbs.article.ArticleDAO;
import com.example.ebrainstudy__springbootbbs.comment.CommentDAO;
import com.example.ebrainstudy__springbootbbs.file.FileDAO;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteArticleService {
	private final ArticleDAO articleDAO;
	private final FileDAO fileDAO;
	private final CommentDAO commentDAO;
	private Integer articleId;

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public void process(HttpServletRequest req, HttpServletResponse res){

		if (commentDAO.selectComments(articleId)!=null){
			commentDAO.deleteCommentOnArticle(articleId);
		}
		if (fileDAO.getFiles(articleId) != null){
			fileDAO.deleteFileOnArticle(articleId);}
		articleDAO.deleteArticle(articleId);
		try {
			res.sendRedirect("/");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}




}
