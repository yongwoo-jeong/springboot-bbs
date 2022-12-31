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

@Service
@RequiredArgsConstructor
public class ArticleViewService implements ServiceInterface {
	private final ArticleDAO articleDAO;
	private final CommentDAO commentDAO;
	private final FileDAO fileDAO;
	private Integer targetArticleId;
	public void setTargetArticleId(Integer targetArticleId) {
		this.targetArticleId = targetArticleId;
	}
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
