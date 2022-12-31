package com.example.ebrainstudy__springbootbbs.article;

import com.example.ebrainstudy__springbootbbs.batisMapper.MapperMaker;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * 게시글 CRUD 를 위한 DAO
 */
@Component
public class ArticleDAO {
	/**
	 * article 테이블을 위한 마이바티스 매퍼 인터페이스
	 */
	private final ArticleMapperInterface mapper;
	public ArticleDAO(MapperMaker mapperMaker){
		this.mapper = mapperMaker.getArticleMapper();
	}
	/**
	 * index page, 검색 조건에 따라 아티클 인스턴스 리스트를 리턴해주는 메서드
	 * @param limitStartOffset 페이징을 위한 DB LIMIT OFFSET
	 * @param searchCondition 기간, 키워드, 카테고리 정보가 담긴 Map 객체
	 * @return List<ArticleVO>
	 */
	public List<ArticleVO> getSearchedArticles(int limitStartOffset,Map searchCondition) {
		return mapper.selectSearchArticles(limitStartOffset,searchCondition);
	}

	/**
	 * 검색 조건과 일치하는 article COUNT를 반환하는 메서드
	 * @param searchConditionMap
	 * @return
	 */
	public int getArticlesCount(Map<String, Object> searchConditionMap){
		return mapper.selectCountArticles(searchConditionMap);
	}
	public ArticleVO getArticle(int articleId){
		mapper.updateViewCount(articleId);
		return mapper.selectArticle(articleId);
	}
	public void insertNewArticle(ArticleVO newArticle){
		mapper.insertArticle(newArticle);
	}
}
