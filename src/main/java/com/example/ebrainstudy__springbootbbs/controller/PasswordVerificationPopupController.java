package com.example.ebrainstudy__springbootbbs.controller;

import com.example.ebrainstudy__springbootbbs.service.DeleteArticleService;
import com.example.ebrainstudy__springbootbbs.service.PasswordVerificationService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class PasswordVerificationPopupController {
	private final PasswordVerificationService passwordVerificationService;
	private final DeleteArticleService deleteArticleService;

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
		}  else {
			// 비밀번호가 일치할 경우 수정 혹은 삭제 알맞는 페이지로 리다이렉트
		if ("del".equals(action)) {
			deleteArticleService.setArticleId(id);
			deleteArticleService.process(req, res);
			try {
				String redirectPage = "http://localhost:8080/";
				res.setContentType("text/html; charset=utf-8");
				PrintWriter out = res.getWriter();
				out.println("<script> window.opener.location.href='"+ redirectPage + "'; window.close(); </script>");
				System.out.println("<script>opener.location.href='"+ redirectPage + "'; window.close(); </script>");
				out.flush();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		}
//		} else if ("modi".equals(action)) {
//
//		}
//	}
		return action;
	}


}
