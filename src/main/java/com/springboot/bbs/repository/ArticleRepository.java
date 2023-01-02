package com.springboot.bbs.repository;

import com.springboot.bbs.vo.ArticleVO;
import com.springboot.bbs.vo.SearchCriteriaVO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleRepository {
	List<ArticleVO> selectSearchArticles(Integer limitOffset, SearchCriteriaVO searchCriteria);
}
