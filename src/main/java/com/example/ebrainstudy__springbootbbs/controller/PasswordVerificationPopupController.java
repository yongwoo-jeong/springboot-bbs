package com.example.ebrainstudy__springbootbbs.controller;

import com.example.ebrainstudy__springbootbbs.service.PasswordVerificationService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PasswordVerificationPopupController {
	private final PasswordVerificationService passwordVerificationService;
	public PasswordVerificationPopupController(PasswordVerificationService passwordVerificationService){
		this.passwordVerificationService = passwordVerificationService;
	}
	@GetMapping("/passwordVerificationPopup")
	public String modifyArticlePopup(HttpServletRequest req, @RequestParam String action, @RequestParam String id){
		req.setAttribute("action", action);
		req.setAttribute("articleId", id);
		return "popup/passwordVerification";
	}

	@PostMapping("/passwordVerify")
	public void passwordVerify(HttpServletRequest req, HttpServletResponse res,
								@RequestParam String action, @RequestParam Integer id,
								@RequestParam("password") String password ){
		boolean isPasswordCorrect = passwordVerificationService.passwordVerification(password, id);
		if (!isPasswordCorrect){
			try {
				String redirectPage = "http://localhost:8080/passwordVerificationPopup?action="+action+"&id="+id;
				res.setContentType("text/html; charset=euc-kr");
				PrintWriter out = res.getWriter();
				out.println("<script>alert('비밀번호가 틀립니다');  window.location.href='"+redirectPage+"';"+"</script>");
				out.flush();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}
	}
}
