package com.example.ebrainstudy__springbootbbs.searchCondition;

import lombok.Getter;
import lombok.Setter;
import utils.IsEmpty;

/**
 * 검색 조건에 따른 쿼리스트링 만들어주는 클래스
 */
public class SearchCondition {
	@Getter
	@Setter
	private static SearchConditionVO savedCondition;
	/**
	 * 검색 조건에 따른 쿼리스트링 만들어주는 메서드
	 * @param searchCondition 검색 조건 VO
	 * @return 검색 조건을 담고있는 쿼리 스트링
	 */
	 public String makeQuerystring(SearchConditionVO searchCondition){
		StringBuilder querystring = new StringBuilder();
		// 초깃값을 빈 스트링으로 만들어서 null 로 삽입되는걸 방지
		String keyword = "";
		String startDate = "";
		String endDate = "";
		// 각 검색 조건이 있을 경우 value 교체
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
