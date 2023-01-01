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

/**
 * 삭제/수정 시 팝업레이어로 전달되는 요청을 처리하는 컨트롤러
 */
@Controller
public class PasswordVerificationPopupController {

	/**
	 * 패스워드 검증 서비스 
	 */
	private final PasswordVerificationService passwordVerificationService;
	/**
	 * 게시글 삭제 서비스 컴포넌트
	 */
	private final DeleteArticleService deleteArticleService;
	@Autowired
	public PasswordVerificationPopupController(PasswordVerificationService passwordVerificationService,
												DeleteArticleService deleteArticleService){
		this.passwordVerificationService = passwordVerificationService;
		this.deleteArticleService = deleteArticleService;
	}

	/**
	 * 팝업레이어 GET 요청 컨트롤러
	 * @param req 애트리뷰트를 설정하기 위한 HttpServletRequest 객체
	 * @param action 삭제(del) or 수정(modi) 요청중 하나가 담긴 파라미터
	 * @param id 게시글 ID
	 * @return
	 */
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
