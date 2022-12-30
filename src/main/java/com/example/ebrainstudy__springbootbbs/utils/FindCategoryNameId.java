package com.example.ebrainstudy__springbootbbs.utils;

import java.util.Objects;

/**
 * 카테고리 ID 받아서 카테고리 이름으로 반환해주는 클래스
 */
public class FindCategoryNameId {
	private String category;
	private Integer category_id;
	public Integer findCategoryIdFn(String category){
		if (Objects.equals(category, "All")){
			category_id = 0;
		}
		else if(Objects.equals(category,"JAVA")) {
			category_id = 1;
		} else if (Objects.equals(category,"Javascript")) {
			category_id = 2;
		} else if(Objects.equals(category, "Database")) {
			category_id = 3;
		} else {
			System.out.println("Category ID out of range.");
		}
		return category_id;
	}
	public String findCategoryName(Integer categoryId) {
		if( categoryId == 1 ) {
			category = "JAVA";
		} else if (categoryId == 2) {
			category = "Javascript";
		} else if (categoryId == 3) {
			category = "Database";
		} else{
			category = "Wrong category";
		}
		return category;
	}

}