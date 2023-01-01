package com.example.ebrainstudy__springbootbbs.controller;

import com.example.ebrainstudy__springbootbbs.service.InsertCommentService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 댓글 입력 요청 처리하는 컨트롤러
 */
@Controller
public class AddCommentController {

	/**
	 * 서비스 컴포넌트 의존성 주입
	 */
	private InsertCommentService insertCommentService;
	public AddCommentController(InsertCommentService insertCommentService){
		this.insertCommentService = insertCommentService;
	}

	/**
	 * ArticleView 에서 입력받은 댓글을
	 * POST 요청받아 서비스 컴포넌트로 보내는 컨트롤러
	 * @param req
	 * @param id
	 * @return
	 */
	@PostMapping("/addComment")
	public String addComment(HttpServletRequest req, @RequestParam int id){
		String commentContent = req.getParameter("new_comment");
		insertCommentService.setCommentContent(commentContent);
		insertCommentService.setArticleId(id);
		insertCommentService.process();
		String redirectUrl = "redirect:article?id="+ id;
		return redirectUrl;
	}
}
