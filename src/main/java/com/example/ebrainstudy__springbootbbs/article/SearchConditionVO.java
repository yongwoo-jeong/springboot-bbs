package com.example.ebrainstudy__springbootbbs.article;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 검색 조건 VO
 */
@Setter
@Getter
public class SearchConditionVO {
	/**
	 * 검색 키워드
	 */
	private String keyword;
	/**
	 * 검색 카테고리
	 * 전체 - All
	 */
	private String category;
	/**
	 * 게시글 등록/수정 기간 조회를 위한 시작날짜
	 * 매퍼에 들어가기 전 리스트 List 로 파싱 필요함
	 */
	private Date startDate;
	/**
	 * 게시글 등록/수정 기간 조회를 위한 마지막날짜
	 */
	private Date endDate;
	/**
	 * 페이징을 위한 현재 페이지 정보
	 */
	private int currentPage = 1;

}
