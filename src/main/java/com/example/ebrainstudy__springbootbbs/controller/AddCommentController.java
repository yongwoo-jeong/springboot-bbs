package com.example.ebrainstudy__springbootbbs.controller;

import com.example.ebrainstudy__springbootbbs.service.InsertCommentService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddCommentController {
	private InsertCommentService insertCommentService;
	public AddCommentController(InsertCommentService insertCommentService){
		this.insertCommentService = insertCommentService;
	}
	@PostMapping("/addComment")
	public String addComment(HttpServletRequest req, @RequestParam int id){
		String commentContent = req.getParameter("new_comment");
		System.out.println(commentContent);
		System.out.println(id);
		insertCommentService.setCommentContent(commentContent);
		insertCommentService.setArticleId(id);
		insertCommentService.process();
		String redirectUrl = "redirect:article?id="+ id;
		return redirectUrl;
	}
}
