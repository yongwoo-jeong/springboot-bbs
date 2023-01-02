package com.springboot.bbs.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCriteriaVO {
	private String keyword;
	private String categoryName;
	private String startDate;
	private String endDate;
	private Integer currentPage=1;
}
