package com.springboot.bbs.service;

import com.springboot.bbs.repository.CommentRepository;
import com.springboot.bbs.vo.CommentVO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 댓글 서비스
 */
@Service
@RequiredArgsConstructor
public class CommentService {

	/**
	 * 코멘트 레포지토리 생성자
	 */
	private final CommentRepository commentRepository;

	/**
	 * 게시글에 딸린 댓글 리스트를 컨트롤러로 보내주는 서비스
	 * @param articleId 해당 게시글 id
	 * @return
	 */
	public List<CommentVO> getCommentList(Integer articleId){
		return commentRepository.selectComments(articleId);
	}

	/**
	 * 새 댓글 추가 서비스 메서드
	 * @param articleId 댓글 입력 대상 게시글 ID
	 * @param newComment 새 댓글 객체
	 */
	public void addCommentService(Integer articleId, CommentVO newComment){
		newComment.setArticleId(articleId);
		commentRepository.insertComment(newComment);
	}
}
