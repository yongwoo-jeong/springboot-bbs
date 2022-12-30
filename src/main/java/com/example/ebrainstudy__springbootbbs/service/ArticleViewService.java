package com.example.ebrainstudy__springbootbbs.service;

import com.example.ebrainstudy__springbootbbs.article.ArticleDAO;
import com.example.ebrainstudy__springbootbbs.article.ArticleVO;
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
	private final FileDAO fileDAO;
	private Integer targetArticleId;
	public void setTargetArticleId(Integer targetArticleId) {
		this.targetArticleId = targetArticleId;
	}
	@Override
	public void process(HttpServletRequest req, HttpServletResponse res, SearchConditionVO searchCondition){
		ArticleVO targetArticle = articleDAO.getArticle(targetArticleId);
		List<FileVO> filesOnArticle = fileDAO.getFiles(targetArticleId);
		req.setAttribute("article", targetArticle);
		req.setAttribute("fileList", filesOnArticle);
	}
}
