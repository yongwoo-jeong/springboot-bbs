package com.springboot.bbs.dto;

import com.springboot.bbs.vo.ArticleVO;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleDTO {
	private List<ArticleVO> searchedArticles;
	private Integer searchedArticlesCount;

}
