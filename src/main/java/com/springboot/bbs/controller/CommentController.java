package com.springboot.bbs.controller;

import com.springboot.bbs.service.CommentService;
import com.springboot.bbs.vo.CommentVO;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 댓글 컨트롤러
 */
@Controller
@RequiredArgsConstructor
public class CommentController {
	private final CommentService commentService;

	@PostMapping("/addComment")
	public String addComment(HttpServletRequest req, @RequestParam("id") Integer articleId,@ModelAttribute CommentVO newComment){
		commentService.addCommentService(articleId,newComment);
		// 댓글 POST 요청 후 이전 페이지로 돌리기 위한 referer
		String refererPage = req.getHeader("referer");
		return "redirect:"+refererPage;
	}
}
