package com.springboot.bbs.vo;

import java.sql.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * 댓글 VO
 */
@Getter
@Setter
@Builder
public class CommentVO {

	/**
	 * 코멘트 ID
	 */
	private Integer commentId;

	/**
	 * 댓글 내용
	 */
	@NonNull
	private String content;

	/**
	 * 댓글 생성일시
	 */
	private Date createdAt;

	/**
	 * 댓글 속한 게시글 ID (FK)
	 */
	private Integer articleId;
}
