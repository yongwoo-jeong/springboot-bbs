package com.example.ebrainstudy__springbootbbs.service;

import com.example.ebrainstudy__springbootbbs.comment.CommentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 댓글 내용을 전달받아 DAO 통해 INSERT 처리하는 서비스 컴포넌트
 */
@Service
public class InsertCommentService {

	/**
	 * 댓글 INSERT에 필요한 DAO 객체
	 */
	private final CommentDAO commentDAO;
	/**
	 * 댓글내용
	 */
	private String commentContent;
	/**
	 * 댓글이 등록될 게시글 ID (FK)
	 */
	private int articleId;

	/**
	 * 생성자
	 * @param commentDAO
	 */
	@Autowired
	public InsertCommentService(CommentDAO commentDAO){
		this.commentDAO = commentDAO;
	}

	/**
	 * 댓글 내용을 전달받는 세터메서드
	 * @param commentContent
	 */
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	/**
	 * 게시글 ID 를 전달받는 세터메서드
	 * @param articleId
	 */
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	/**
	 * DAO 통해 코멘트 테이블에 INSERT 해주는 메서드₩
	 */
	public void process(){
		commentDAO.insertComment(commentContent, articleId);
	}


}
