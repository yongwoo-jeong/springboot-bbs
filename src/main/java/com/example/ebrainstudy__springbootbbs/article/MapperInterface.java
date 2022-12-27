package com.example.ebrainstudy__springbootbbs.article;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface MapperInterface {
	public List<ArticleVO> selectSearchArticles(@Param("articleOffset") int articleOffset, @Param("conditionMap") Map conditionMap);
	public int selectCountArticles(@Param("conditionMap") Map conditionMap);
}
