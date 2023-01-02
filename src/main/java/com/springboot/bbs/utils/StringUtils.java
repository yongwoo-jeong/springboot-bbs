package com.springboot.bbs.utils;

import com.springboot.bbs.vo.SearchCriteriaVO;

public class StringUtils {
	public static boolean isEmpty(String args){
		return args == null || "".equals(args);
	}

	public static String makeQueryString(SearchCriteriaVO searchCriteria){
		StringBuilder querystring = new StringBuilder();
		// 초깃값을 빈 스트링으로 만들어서 null 로 삽입되는걸 방지
		String keyword = "";
		String startDate = "";
		String endDate = "";
		// 각 검색 조건이 있을 경우 value 교체
		if (!isEmpty(searchCriteria.getKeyword())){
			keyword = searchCriteria.getKeyword();
		}
		if (!isEmpty(searchCriteria.getStartDate())){
			startDate = searchCriteria.getStartDate();
		}
		if (!isEmpty(searchCriteria.getEndDate())){
			endDate = searchCriteria.getEndDate();
		}
		querystring.append("?category="+searchCriteria.getCategoryId());
		querystring.append("&keyword="+keyword);
		querystring.append("&startDate="+startDate);
		querystring.append("&endDate="+endDate);
		querystring.append("&currentPage=");
		return querystring.toString();
	}
}
