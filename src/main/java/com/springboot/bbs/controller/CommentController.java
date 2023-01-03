package com.springboot.bbs.controller;

import com.springboot.bbs.service.CommentService;
import com.springboot.bbs.vo.CommentVO;
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
	private CommentService commentService;

	@PostMapping("/addComment")
	public void addComment(@RequestParam("id") Integer articleId,@ModelAttribute CommentVO newComment){

	}
}
