package com.springboot.bbs.service;

import com.springboot.bbs.dto.ArticleDTO;
import com.springboot.bbs.repository.ArticleRepository;
import com.springboot.bbs.vo.ArticleVO;
import com.springboot.bbs.vo.SearchCriteriaVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 게시글 관련 서비스객체
 */
@Service
@RequiredArgsConstructor
public class ArticleService {

	/**
	 * 게시글 관련 데이터 리포지토리 객체
	 */
	private final ArticleRepository articleRepository;

	/**
	 * 홈화면을 보여주기 위해 검색된 게시글(혹은 전체게시글)과
	 * 게시글 수 카운트를 가져오는 서비스
	 * @param searchCriteria 검색조건(없을 경우 쿼리문 TRIM되어 전체게시글 검색)
	 * @return
	 */
	public ArticleDTO homeService(SearchCriteriaVO searchCriteria){
		ArticleDTO articleDTO = new ArticleDTO();
		articleDTO.setSearchedArticlesCount((articleRepository.selectCountArticles(searchCriteria)));
		articleDTO.setSearchedArticles(articleRepository.selectSearchArticles(searchCriteria));
		return articleDTO;
	}

	/**
	 * 개별 게시글을 보기위해 해당 레코드를 가져오는 서비스메서드
	 * @param articleId 대상 아티클 ID
	 * @return
	 */
	public ArticleVO articleDetailService(Integer articleId){
		// 조회수 +1
		articleRepository.updateViewCount(articleId);
		return articleRepository.selectArticleDetail(articleId);
	}
}
