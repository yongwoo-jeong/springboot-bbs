package com.springboot.bbs.utils;

import com.springboot.bbs.vo.SearchCriteriaVO;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 문자열 관련 유틸리티
 */
public class StringUtils {

	/**
	 * 공백문자열 혹은 null 값이 들어올 경우 True 반환하는 메서드
	 * @param args null 확인, 혹은 toString으로 변환 후 공백문자열인지 확인
	 * @return boolean
	 */
	public static boolean isEmpty(Object args){
		return args == null || "".equals(args.toString());
	}

	/**
	 * 검색 조건을 받아 쿼리스트링 파라미터 문자열로 변환해주는 메서드
	 * 페이징 처리 위함
	 * @param searchCriteria SearchCriteriaVO
	 * @return 쿼리스트링 파라미터 문자열
	 */
	public static String makeQueryString(SearchCriteriaVO searchCriteria){
		StringBuilder querystring = new StringBuilder();
		// 초깃값을 빈 스트링으로 만들어서 null 로 삽입되는걸 방지
		String keyword = "";
		String startDate = "";
		String endDate = "";
		String categoryId = "";
		// 각 검색 조건이 있을 경우 value 교체
		if (!isEmpty(searchCriteria.getCategoryId())){
			categoryId = String.valueOf(searchCriteria.getCategoryId());
		}
		if (!isEmpty(searchCriteria.getKeyword())){
			keyword = URLEncoder.encode(searchCriteria.getKeyword(), StandardCharsets.UTF_8);
		}
		if (!isEmpty(searchCriteria.getStartDate())){
			startDate = searchCriteria.getStartDate();
		}
		if (!isEmpty(searchCriteria.getEndDate())){
			endDate = searchCriteria.getEndDate();
		}
		querystring.append("?categoryId=").append(categoryId);
		querystring.append("&keyword=").append(keyword);
		querystring.append("&startDate=").append(startDate);
		querystring.append("&endDate=").append(endDate);
		querystring.append("&currentPage=");
		return querystring.toString();
	}
}
