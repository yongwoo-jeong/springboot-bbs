package com.example.ebrainstudy__springbootbbs;

import com.example.ebrainstudy__springbootbbs.article.ArticleDAO;
import com.example.ebrainstudy__springbootbbs.article.ArticleVO;
import com.example.ebrainstudy__springbootbbs.pageHandler.IndexPageHandler;
import com.example.ebrainstudy__springbootbbs.pageHandler.InputArticlePageHandler;
import com.example.ebrainstudy__springbootbbs.searchCondition.SearchConditionVO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	@RequestMapping("/")
	public String  homeController(HttpServletRequest req, HttpServletResponse res, SearchConditionVO searchConditionParameter){
		// DAO 가 static 으로 선언되어야하나?
		IndexPageHandler indexPageHandler = new IndexPageHandler(new ArticleDAO());
		indexPageHandler.setSearchCondition(searchConditionParameter);
		indexPageHandler.process(req,res);
		return "index";
	}
	@GetMapping("/upload")
	public String inputNewArticleController(){
		return "newArticleInputForm";
	}
	@PostMapping("/upload")
	public void postNewArticle(HttpServletRequest req, HttpServletResponse res,@ModelAttribute ArticleVO createdArticle) throws ServletException {
		InputArticlePageHandler inputHandler = new InputArticlePageHandler(new ArticleDAO());
		inputHandler.setInsertingArticle(createdArticle);
		inputHandler.process(req,res);

	}
}
