package com.springboot.bbs.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 카테고리 VO
 */
@Getter
@Setter
public class CategoryVO {

	/**
	 * 카테고리 ID
	 */
	private Integer categoryId;

	/**
	 * 카테고리명
	 */
	private String categoryName;
}
