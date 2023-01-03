package com.springboot.bbs.dto;

import com.springboot.bbs.vo.ArticleVO;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleDTO {
	private String title;
	private String writer;
	private String password;
	private String passwordConfirm;
	private String content;
	private String categoryIdAndName;
	private List<ArticleVO> searchedArticles;
	private Integer searchedArticlesCount;

}
