package com.example.ebrainstudy__springbootbbs.batisMapper;

import com.example.ebrainstudy__springbootbbs.article.ArticleVO;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ArticleMapperInterface {
	public List<ArticleVO> selectSearchArticles(@Param("limitStartOffset") int limitStartOffset, @Param("conditionMap") Map conditionMap);
	public int selectCountArticles(@Param("conditionMap") Map conditionMap);
	public ArticleVO selectArticle(@Param("articleId") int articleId);
	public void updateViewCount(@Param("articleId") int articleId);
	public void insertArticle(@Param("newArticle")ArticleVO articleVO);
}
