package com.example.ebrainstudy__springbootbbs;

import com.example.ebrainstudy__springbootbbs.article.ArticleDAO;
import com.example.ebrainstudy__springbootbbs.article.SearchConditionVO;
import com.example.ebrainstudy__springbootbbs.pageHandler.IndexPageHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	@RequestMapping("/")
	public String  homeController(HttpServletRequest req, HttpServletResponse res, SearchConditionVO searchCondition){
		IndexPageHandler indexPageHandler = new IndexPageHandler(new ArticleDAO());
		indexPageHandler.setSearchCondition(searchCondition);
		indexPageHandler.process(req,res);
		return "index";
	}
}
