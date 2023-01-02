package com.example.ebrainstudy__springbootbbs.comment;

import java.sql.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommentVO {
	private Integer commentId;
	@NonNull
	private String content;
	private Date createdAt;
	private Integer articleId;
}
