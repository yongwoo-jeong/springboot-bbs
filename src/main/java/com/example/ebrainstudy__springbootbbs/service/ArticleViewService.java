package com.example.ebrainstudy__springbootbbs.service;

import com.example.ebrainstudy__springbootbbs.article.ArticleDAO;
import com.example.ebrainstudy__springbootbbs.article.ArticleVO;
import com.example.ebrainstudy__springbootbbs.comment.CommentDAO;
import com.example.ebrainstudy__springbootbbs.comment.CommentVO;
import com.example.ebrainstudy__springbootbbs.file.FileDAO;
import com.example.ebrainstudy__springbootbbs.file.FileVO;
import com.example.ebrainstudy__springbootbbs.searchCondition.SearchConditionVO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 개별 게시글 페이지 요청 서비스 로직
 */
@Service
@RequiredArgsConstructor
public class ArticleViewService implements ServiceInterface {

	/**
	 * 게시글 정보를 가져오기 위한 게시글 DAO
	 */
	private final ArticleDAO articleDAO;
	/**
	 * 게시글에 달린 댓글을 가져오기 위한 코멘트 DAO
	 */
	private final CommentDAO commentDAO;
	/**
	 * 게시글에 달린 파일을 가져오기 위한 파일 DAO
	 */
	private final FileDAO fileDAO;
	/**
	 * URL 파라미터로 받아오는 게시글 ID (article_id)
	 */
	private Integer targetArticleId;

	/**
	 * 게시글 ID 세터 메서드
	 * @param targetArticleId
	 */
	public void setTargetArticleId(Integer targetArticleId) {
		this.targetArticleId = targetArticleId;
	}

	/**
	 * 게시글, 댓글, 파일을 DAO 통해 받아와 애트리뷰트 형태로 뷰에 전달하는 프로세스 메서드
	 * @param req
	 * @param res
	 * @param searchCondition 검색 조건 유지를 위한 서치컨디션 객체를 파라미터로 받는다
	 */
	@Override
	public void process(HttpServletRequest req, HttpServletResponse res, SearchConditionVO searchCondition){
		ArticleVO targetArticle = articleDAO.getArticle(targetArticleId);
		List<FileVO> filesOnArticle = fileDAO.getFiles(targetArticleId);
		List<CommentVO> commentList = commentDAO.selectComments(targetArticleId);
		req.setAttribute("article", targetArticle);
		req.setAttribute("commentList", commentList);
		req.setAttribute("fileList", filesOnArticle);
	}
}
