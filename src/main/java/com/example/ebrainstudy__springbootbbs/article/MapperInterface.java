package com.example.ebrainstudy__springbootbbs.article;

import java.util.List;
import java.util.Map;

public interface MapperInterface {
	public List<ArticleVO> selectSearchArticles();
	public List<ArticleVO> selectSearchArticles(Map searchCondition);
	public int selectCountArticles();
}
