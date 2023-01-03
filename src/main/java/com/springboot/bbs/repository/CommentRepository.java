package com.springboot.bbs.repository;

import com.springboot.bbs.vo.CommentVO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

/**
 * 댓글 레포지토리
 */
@Repository
@RequiredArgsConstructor
public class CommentRepository {

	/**
	 * 매퍼 ID 생성자
	 */
	private final String mapperNameSpace = "com.springboot.bbs.comment.CommentMapperInterface";

	/**
	 * SqlSession 생성자
	 */
	private final SqlSession session;

	/**
	 * articleDetail 페이지에 필요한 댓글리스트 SELECT
	 * @param articleId 게시글 ID
	 * @return List of comment
	 */
	public List<CommentVO> selectComments(Integer articleId){
		return session.selectList(mapperNameSpace+".selectComments", articleId);
	}

	/**
	 * articleDetail input 으로 받은 댓글 INSERT
	 * @param commentVO 새 댓글
	 */
	public void insertComment(CommentVO commentVO){
		session.insert(mapperNameSpace+".insertComment",commentVO);
	}

}
