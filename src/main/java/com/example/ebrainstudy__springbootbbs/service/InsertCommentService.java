package com.example.ebrainstudy__springbootbbs.service;

import com.example.ebrainstudy__springbootbbs.comment.CommentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsertCommentService {
	private final CommentDAO commentDAO;
	private String commentContent;
	private int articleId;
	@Autowired
	public InsertCommentService(CommentDAO commentDAO){
		this.commentDAO = commentDAO;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public void process(){
		commentDAO.insertComment(commentContent, articleId);
	}


}
