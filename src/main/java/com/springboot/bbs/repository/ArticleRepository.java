package com.springboot.bbs.repository;

import com.springboot.bbs.vo.ArticleVO;
import com.springboot.bbs.vo.SearchCriteriaVO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 서비스단에서 요청받아 DB 데이터 반환하는
 * 아티클 레포지토리
 */
@Mapper
public interface ArticleRepository {

	/**
	 * 조건에 맞는 게시글객체를 List 형태로 반환
	 * @param searchCriteria 검색조건 (searchCriteriaVO)
	 * @return List of ArticleVO
	 */
	List<ArticleVO> selectSearchArticles(@Param("searchCriteria") SearchCriteriaVO searchCriteria);
	/**
	 * 조건에 맞는 게시글 수를 반환
	 * @param searchCriteria 검색조건 (searchCriteriaVO)
	 * @return 검색된 게시글 수
	 */
	Integer selectCountArticles(@Param("searchCriteria") SearchCriteriaVO searchCriteria);

	/**
	 * 개별 게시글 정보(튜플)을 서비스에 반환
	 * @param articleId 대상 게시글 ID
	 * @return ArticleVO
	 */
	ArticleVO selectArticleDetail(@Param("articleId") Integer articleId);

	/**
	 * 게시글 조회수 +1
	 * @param articleId 대상 게시글 ID
	 */
	void updateViewCount(@Param("articleId") Integer articleId);

	/**
	 * 새 게시글을 DB INSERT
	 * @param newArticle ArticleVO
	 */
	void insertArticle(@Param("newArticle") ArticleVO newArticle);
}
