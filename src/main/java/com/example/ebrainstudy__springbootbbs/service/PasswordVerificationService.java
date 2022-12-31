package com.example.ebrainstudy__springbootbbs.service;

import com.example.ebrainstudy__springbootbbs.article.ArticleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordVerificationService {
	private final ArticleDAO articleDAO;
	@Autowired
	public PasswordVerificationService(ArticleDAO articleDAO){
		this.articleDAO = articleDAO;
	}
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
