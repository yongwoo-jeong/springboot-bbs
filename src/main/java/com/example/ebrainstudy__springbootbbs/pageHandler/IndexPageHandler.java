package com.example.ebrainstudy__springbootbbs.pageHandler;

import com.example.ebrainstudy__springbootbbs.article.ArticleDAO;
import com.example.ebrainstudy__springbootbbs.article.ArticleVO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * index 페이지에서 전체 게시글 혹은 검색 조건에 따른
 * 게시글을 모델에서 뷰로 전달하는 컨트롤러
 */
@RequiredArgsConstructor
public class IndexPageHandler implements PageCommandHandler {
	private final ArticleDAO articleDAO;
	@Override
	public void process(HttpServletRequest req, HttpServletResponse res){
		Map<String, Object> searchCondition = new HashMap<>();
		int currentPage = 1;
		searchCondition.put("articleLimitTo", currentPage*10);
		List<ArticleVO> searchedArticles = articleDAO.searchArticles(searchCondition);
		int articlesCount = articleDAO.getCountArticles();
		req.setAttribute("articles", searchedArticles);
		req.setAttribute("articlesCount",articlesCount);
		req.setAttribute("currentPage",currentPage);
	}
}
