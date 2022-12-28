package com.example.ebrainstudy__springbootbbs.article;

import com.example.ebrainstudy__springbootbbs.MapperMaker;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class ArticleDAO {
	/**
	 * index page, 검색 조건 토대로 해당되는 아티클 인스턴스 리스트를 리턴해주는 메서드
	 * @param selectMap 날짜, 키워드, 카테고리, 페이징 정보가 담긴 Map
	 * @return List<ArticleVO>
	 */
	public List<ArticleVO> searchArticles(int limitStartOffset,Map searchCondition) {
		ArticleMapperInterface mapper = new MapperMaker().getArticleMapper();
		List<ArticleVO> articleVOFromMapper = mapper.selectSearchArticles(limitStartOffset,searchCondition);
		return articleVOFromMapper;
	}
	public int getCountArticles(Map<String, Object> searchConditionMap){
		ArticleMapperInterface mapper = new MapperMaker().getArticleMapper();
		return mapper.selectCountArticles(searchConditionMap);
	}
}
