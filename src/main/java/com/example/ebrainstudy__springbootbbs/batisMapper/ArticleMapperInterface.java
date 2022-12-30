package com.example.ebrainstudy__springbootbbs.batisMapper;

import com.example.ebrainstudy__springbootbbs.article.ArticleVO;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * 게시글 CRUD 를 위한 article table 매퍼 인터페이스
 */
public interface ArticleMapperInterface {

	/**
	 * 검색 조건과 SELECT 리밋 오프셋을 받아
	 * 게시글 검색 결과 리스트를 반환해주는 메서드
	 * @param limitStartOffset SELECT FROM article LIMIT {OFFSET}
	 * @param conditionMap 검색조건
	 * @return List of Article
	 */
	public List<ArticleVO> selectSearchArticles(@Param("limitStartOffset") int limitStartOffset, @Param("conditionMap") Map conditionMap);

	/**
	 * 검색 조건에 맞는 게시글 수를 리턴해주는 메서드
	 * @param conditionMap 검색조건 - selectSearchArticle 과 동일한 객체
	 * @return 조건에 부합하는 게시글 수
	 */
	public int selectCountArticles(@Param("conditionMap") Map conditionMap);

	/**
	 * 게시글 ID를 받아 게시글 정보 (ArticleVO)를 반환하는 메서드
	 * @param articleId 해당되는 게시글 ID
	 * @return 게시글 정보가 담긴 DTO(ArticleVO)
	 */
	public ArticleVO selectArticle(@Param("articleId") int articleId);
	public void updateViewCount(@Param("articleId") int articleId);
	public void insertArticle(@Param("newArticle")ArticleVO articleVO);
}
