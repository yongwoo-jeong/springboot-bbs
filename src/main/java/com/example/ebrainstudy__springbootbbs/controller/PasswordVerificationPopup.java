package com.example.ebrainstudy__springbootbbs.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PasswordVerificationPopup {
	@GetMapping("/passwordVerificationPopup")
	public String modifyArticlePopup(HttpServletRequest req,@RequestParam String action){
		req.setAttribute("action", action);
		return "popup/passwordVerification";
	}
}
