package com.springboot.bbs.dto;

import com.springboot.bbs.vo.ArticleVO;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * 게시글 관련 DTO
 */
@Getter
@Setter
public class ArticleDTO {
	/**
	 * 검색조건에 맞는 게시글 리스트
	 * used in homeService <-> homeController
	 */
	private List<ArticleVO> searchedArticles;

	/**
	 * 검색조건에 맞는 게시글 수
	 * used in homeService <-> homeController
	 */
	private Integer searchedArticlesCount;

	/**
	 * '/upload' 새 게시글 Form input 제목 필드
	 */
	private String title;

	/**
	 * '/upload' 새 게시글 Form input 작성자 필드
	 */
	private String writer;

	/**
	 * '/upload' 새 게시글 Form input 비밀번호 필드
	 */
	private String password;

	/**
	 * '/upload' 새 게시글 Form input 비밀번호 확인 필드
	 */
	private String passwordConfirm;

	/**
	 * '/upload' 새 게시글 Form input 내용 필드
	 */
	private String content;

	/**
	 * '/upload' 새 게시글 카테고리선택 select 필드
	 */
	private String categoryIdAndName;




}
