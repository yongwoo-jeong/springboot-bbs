package com.springboot.bbs.repository;

import com.springboot.bbs.vo.ArticleVO;
import com.springboot.bbs.vo.SearchCriteriaVO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleRepository {
	List<ArticleVO> selectSearchArticles(@Param("searchCriteria") SearchCriteriaVO searchCriteria);
	Integer selectCountArticles(@Param("searchCriteria") SearchCriteriaVO searchCriteria);
	ArticleVO selectArticleDetail(@Param("articleId") Integer articleId);
	void updateViewCount(@Param("articleId") Integer articleId);
}
