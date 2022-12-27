package com.example.ebrainstudy__springbootbbs.pageHandler;

import com.example.ebrainstudy__springbootbbs.article.ArticleDAO;
import com.example.ebrainstudy__springbootbbs.article.ArticleVO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * index 페이지에서 전체 게시글 혹은 검색 조건에 따른
 * 게시글을 모델에서 뷰로 전달하는 컨트롤러
 */
@Controller
public class IndexHandler implements PageCommandHandler {
	@Override
	@GetMapping("/")
	public String process(HttpServletRequest req, HttpServletResponse res){
		ArticleDAO articleDAO = new ArticleDAO();
		List<ArticleVO> searchedArticles = articleDAO.searchArticles();
		int articlesCount = articleDAO.getCountArticles();
		req.setAttribute("articles", searchedArticles);
		req.setAttribute("articlesCount",articlesCount);
		return "index";
	}
}
