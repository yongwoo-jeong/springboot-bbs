package com.example.ebrainstudy__springbootbbs.searchCondition;

import utils.IsEmpty;

public class SearchConditionMaker {
	 public static String makeQuerystring(SearchConditionVO searchCondition){
		StringBuilder querystring = new StringBuilder();
		String keyword = "";
		String startDate = "";
		String endDate = "";
		if (!IsEmpty.main(searchCondition.getKeyword())){
			keyword = searchCondition.getKeyword();
		}
		if (!IsEmpty.main(searchCondition.getStartDate())){
			startDate = searchCondition.getStartDate();
		}
		if (!IsEmpty.main(searchCondition.getEndDate())){
			endDate = searchCondition.getEndDate();
		}
		querystring.append("?category="+searchCondition.getCategory());
		querystring.append("&keyword="+keyword);
		querystring.append("&startDate="+startDate);
		querystring.append("&endDate="+endDate);
		querystring.append("&currentPage=");
		return querystring.toString();
	}
}
