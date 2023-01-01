package com.example.ebrainstudy__springbootbbs.service;

import com.example.ebrainstudy__springbootbbs.article.ArticleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 게시글 수정, 삭제시 팝업레이어에서 제공되는 비밀번호 검증 서비스
 */
@Service
public class PasswordVerificationService {

	/**
	 * article table 저장된 비밀번호를 가져오기 위한 DAO객체
	 */
	private final ArticleDAO articleDAO;
	@Autowired
	public PasswordVerificationService(ArticleDAO articleDAO){
		this.articleDAO = articleDAO;
	}

	/**
	 * 유저가 입력한 비밀번호와 DB에 저장된 비밀번호 비교
	 * @param userInputPassword 유저가 input에 입력한 비밀번호
	 * @param articleId 요청된 게시글 ID
	 * @return true/false 로 검증결과 리턴
	 */
	public boolean passwordVerification(String userInputPassword, Integer articleId){
		String serverPassword;
		serverPassword = articleDAO.getArticle(articleId).getPassword();
		if (serverPassword.equals(userInputPassword)){
			return true;
		}else{
			return false;
		}
	}
}
