package com.example.ebrainstudy__springbootbbs.searchCondition;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * 검색 조건 VO
 */
@Setter
@Getter
@Component
public class SearchConditionVO {
	/**
	 * 검색 키워드
	 */
	private String keyword;
	/**
	 * 검색 카테고리
	 * 기본값 전체 - All
	 */
	private String category = "All";
	/**
	 * 게시글 등록/수정 기간 조회를 위한 시작날짜
	 * 매퍼에 들어가기 전 리스트 List 로 파싱 필요함
	 */
	private String startDate;
	/**
	 * 게시글 등록/수정 기간 조회를 위한 마지막날짜
	 */
	private String endDate;
	/**
	 * 페이징을 위한 현재 페이지 정보
	 * 기본값 1
	 */
	private int currentPage = 1;

}
