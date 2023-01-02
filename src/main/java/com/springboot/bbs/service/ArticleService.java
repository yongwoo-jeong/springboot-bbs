package com.springboot.bbs.service;

import com.springboot.bbs.dto.ArticleDTO;
import com.springboot.bbs.repository.ArticleRepository;
import com.springboot.bbs.vo.ArticleVO;
import com.springboot.bbs.vo.SearchCriteriaVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {
	private final ArticleRepository articleRepository;
	public ArticleDTO homeService(SearchCriteriaVO searchCriteria){
		ArticleDTO articleDTO = new ArticleDTO();
		articleDTO.setSearchedArticlesCount((articleRepository.selectCountArticles(searchCriteria)));
		articleDTO.setSearchedArticles(articleRepository.selectSearchArticles(searchCriteria));
		return articleDTO;
	}

	public ArticleVO articleDetailService(Integer articleId){
		articleRepository.updateViewCount(articleId);
		return articleRepository.selectArticleDetail(articleId);
	}
}
