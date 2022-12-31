package com.example.ebrainstudy__springbootbbs.comment;

import com.example.ebrainstudy__springbootbbs.batisMapper.MapperMaker;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * 마이배티스 매퍼를 이용해 댓글 관련 데이터 생성, 읽기, 삭제하는 클래스
 */
@Component
public class CommentDAO {
	private CommentMapperInterface commentMapper;
	public CommentDAO(MapperMaker mapperMaker){
		this.commentMapper = mapperMaker.getCommentMapper();
	}

	/**
	 * 게시글에 해당하는 댓글을 List 형태로 반환해주는 메서드
	 * @param id articleId
	 * @return  List of commentVO
	 */
	public List<CommentVO> selectComments(Integer id) {
		List<CommentVO> commentsOnArticle = commentMapper.selectComments(id);
		return commentsOnArticle;
	}

	/**
	 * 댓글내용을 받아 DTO 객체를 만들어 insert 해주는 메서드
	 * @param content 댓글내용
	 */
	public void insertComment(String content, int articleId){
		CommentVO newComment = CommentVO.builder().content(content).articleId(articleId).build();
		commentMapper.insertComment(newComment);
	}

	public void deleteCommentOnArticle(int articleId){
		commentMapper.deleteCommentOnArticle(articleId);
	}
}
