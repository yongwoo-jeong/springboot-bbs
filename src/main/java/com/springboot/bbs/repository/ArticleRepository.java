package com.springboot.bbs.repository;

import com.springboot.bbs.vo.ArticleVO;
import com.springboot.bbs.vo.SearchCriteriaVO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

/**
 * 서비스단에서 요청받아 DB 데이터 반환하는
 * 아티클 레포지토리
 */
@Repository
@RequiredArgsConstructor
public class ArticleRepository {

	/**
	 * 매퍼 네임스페이스 반복구문 스트링 처리
	 */
	private final String mapperName = "com.springboot.bbs.repository.ArticleRepository";

	/**
	 * sql 세션객체 생성자
	 */
	private final SqlSession session;

	/**
	 * 조건에 맞는 게시글객체를 List 형태로 반환
	 * @param searchCriteria 검색조건 (searchCriteriaVO)
	 * @return List of ArticleVO
	 */
	public List<ArticleVO> selectSearchArticles(SearchCriteriaVO searchCriteria){
		return session.selectList(mapperName + ".selectSearchArticles",searchCriteria);
	};

	/**
	 * 조건에 맞는 게시글 수를 반환
	 * @param searchCriteria 검색조건 (searchCriteriaVO)
	 * @return 검색된 게시글 수
	 */
	public Integer selectCountArticles(SearchCriteriaVO searchCriteria){
		return session.selectOne(mapperName + ".selectCountArticles",searchCriteria);
	};

	/**
	 * 개별 게시글 정보(튜플)을 서비스에 반환
	 * @param articleId 대상 게시글 ID
	 * @return ArticleVO
	 */
	public ArticleVO selectArticleDetail(Integer articleId){
		return session.selectOne(mapperName +".selectArticleDetail", articleId);
	};

	/**
	 * 게시글 조회수 +1
	 * @param articleId 대상 게시글 ID
	 */
	public void updateViewCount(Integer articleId){
		session.update(mapperName +".updateViewCount", articleId);
	};

	/**
	 * 새 게시글을 DB INSERT
	 *
	 * @param newArticle ArticleVO
	 * @return
	 */
	public void insertArticle(ArticleVO newArticle) {
		session.insert(mapperName + ".insertArticle", newArticle);
	}
}
