package com.example.ebrainstudy__springbootbbs.controller;

import com.example.ebrainstudy__springbootbbs.service.DeleteArticleService;
import com.example.ebrainstudy__springbootbbs.service.PasswordVerificationService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PasswordVerificationPopupController {
	private final PasswordVerificationService passwordVerificationService;
	private final DeleteArticleService deleteArticleService;
	private int articleId;
	@Autowired
	public PasswordVerificationPopupController(PasswordVerificationService passwordVerificationService,DeleteArticleService deleteArticleService){
		this.passwordVerificationService = passwordVerificationService;
		this.deleteArticleService = deleteArticleService;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	@GetMapping("/passwordVerificationPopup")
	public String modifyArticlePopup(HttpServletRequest req, @RequestParam String action, @RequestParam String id){
		req.setAttribute("action", action);
		req.setAttribute("articleId", id);
		return "popup/passwordVerification";
	}

	@PostMapping("/passwordVerify")
	public String  passwordVerify(HttpServletRequest req, HttpServletResponse res,
								@RequestParam String action, @RequestParam Integer id,
								@RequestParam("password") String password ){
		boolean isPasswordCorrect = passwordVerificationService.passwordVerification(password, id);
		setArticleId(id);
		// 비밀번호가 일치하지 않는 경우 예외처리
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
		if ("del".equals(action)) {
			deleteArticleService.setArticleId(articleId);
			deleteArticleService.process(req, res);
		}
//		} else if ("modi".equals(action)) {
//
//		}
//	}
		return action;
	}


}
