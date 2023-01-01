package com.example.ebrainstudy__springbootbbs.service;

import com.example.ebrainstudy__springbootbbs.article.ArticleDAO;
import com.example.ebrainstudy__springbootbbs.comment.CommentDAO;
import com.example.ebrainstudy__springbootbbs.file.FileDAO;
import com.example.ebrainstudy__springbootbbs.logger.MyLogger;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

/**
 * 게시글 삭제 서비스 컴포넌트
 */
@Service
public class DeleteArticleService {
	MyLogger logger = MyLogger.getLogger();
	String className = MyLogger.getClassName();

	/**
	 * 게시글 삭제 위한 게시글 DAO
	 */
	private final ArticleDAO articleDAO;
	/**
	 * 파일 삭제 위한 파일 DAO
	 */
	private final FileDAO fileDAO;
	/**
	 * 댓글 삭제 위한 댓글 DAO
	 */
	private final CommentDAO commentDAO;
	/**
	 * 삭제 대상 게시글 ID
	 */
	private Integer articleId;

	/**
	 * 생성자
	 * @param articleDAO
	 * @param fileDAO
	 * @param commentDAO
	 */
	private DeleteArticleService(ArticleDAO articleDAO, FileDAO fileDAO, CommentDAO commentDAO){
		this.articleDAO = articleDAO;
		this.fileDAO = fileDAO;
		this.commentDAO = commentDAO;
	}

	/**
	 * 게시글 id 세터메서드
	 * @param articleId
	 */
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	/**
	 * 게시글에 따른 파일, 댓글이 있으면 먼저 삭제 후
	 * 게시글 삭제
	 * @param req
	 * @param res
	 */
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
			logger.severe(className+"IOException 발생");
			logger.severe(String.valueOf(e));
		}
	}




}
