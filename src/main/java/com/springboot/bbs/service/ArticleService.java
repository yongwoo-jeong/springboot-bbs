package com.springboot.bbs.service;

import com.springboot.bbs.repository.ArticleRepository;
import com.springboot.bbs.vo.ArticleVO;
import com.springboot.bbs.vo.SearchCriteriaVO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {
	private final ArticleRepository articleRepository;
	public List<ArticleVO> homeService(Integer limitOffset, SearchCriteriaVO searchCriteria){
		return articleRepository.selectSearchArticles(limitOffset,searchCriteria);
	}
}
