package com.springboot.bbs.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


/**
 * 검색조건 객체
 */
@Getter
@Setter
public class SearchCriteriaVO {

	/**
	 * 검색 키워드
	 */
	private String keyword;

	/**
	 * 카테고리 ID
	 */
	private Integer categoryId;

	/**
	 * 검색조건 시작날짜
	 */
	private String startDate;

	/**
	 * 검색조건 종료날짜
	 */
	private String endDate;

	/**
	 * 페지네이션값, 현재페이지
	 */
	private Integer currentPage=1;

	/**
	 * dbLimit offset.
	 * preparedStatement 사용을 위해서는 currentPage 값으로 연산 불가능..
	 */
	private Integer dbLimitOffset=0;
}
