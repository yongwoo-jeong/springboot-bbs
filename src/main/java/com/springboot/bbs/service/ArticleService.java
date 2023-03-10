package com.springboot.bbs.service;

import com.springboot.bbs.repository.ArticleRepository;
import com.springboot.bbs.repository.CommentRepository;
import com.springboot.bbs.repository.FileRepository;
import com.springboot.bbs.utils.StringUtils;
import com.springboot.bbs.vo.ArticleVO;
import com.springboot.bbs.vo.CategoryVO;
import com.springboot.bbs.vo.CommentVO;
import com.springboot.bbs.vo.SearchCriteriaVO;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 게시글 관련 서비스객체
 */
@Service
@RequiredArgsConstructor
public class ArticleService {

	/**
	 * 게시글 관련 데이터 리포지토리 객체
	 */
	private final ArticleRepository articleRepository;

	/**
	 * 코멘트 레포지토리 생성자
	 */
	private final CommentRepository commentRepository;

	/**
	 * 파일 레포지토리 생성자
	 */
	private final FileRepository fileRepository;

	public List<CategoryVO> selectCategories(){
		return articleRepository.selectCategories();
	}

	/**
	 * 홈화면을 보여주기 위해 검색된 게시글(혹은 전체게시글)과
	 * 게시글 수 카운트를 가져오는 서비스
	 * @param searchCriteria 검색조건(없을 경우 쿼리문 TRIM되어 전체게시글 검색)
	 * @return
	 */
	public List<ArticleVO> selectArticleList(SearchCriteriaVO searchCriteria){
		return articleRepository.selectSearchArticles(searchCriteria);
	}

	public int selectSearchedArticleCount(SearchCriteriaVO searchCriteria){
		return articleRepository.selectCountArticles(searchCriteria);
	}

	/**
	 * 개별 게시글을 보기위해 해당 레코드를 가져오는 서비스메서드
	 * @param articleId 대상 아티클 ID
	 * @return
	 */
	public ArticleVO selectArticleDetail(Integer articleId){
		// 조회수 +1
		articleRepository.updateViewCount(articleId);
		return articleRepository.selectArticleDetail(articleId);
	}

	/**
	 * 게시글에 딸린 댓글 리스트를 컨트롤러로 보내주는 서비스
	 * @param articleId 해당 게시글 id
	 * @return
	 */
	public List<CommentVO> selectCommentList(Integer articleId){
		return commentRepository.selectComments(articleId);
	}

	/**
	 * 새 댓글 추가 서비스 메서드
	 * @param articleId 댓글 입력 대상 게시글 ID
	 * @param newComment 새 댓글 객체
	 */
	public void insertNewComment(Integer articleId, CommentVO newComment){
		newComment.setArticleId(articleId);
		commentRepository.insertComment(newComment);
	}

	/**
	 * 새 게시글 등록 서비스
	 * TODO enum 예외처리 활용..
	 * @param newArticle 새 게시글 정보가 담긴 객체
	 */
	public int insertNewArticle(ArticleVO newArticle, String passwordConfirm){
		int status = validateArticleForm(newArticle, passwordConfirm);
		if (status!=0){
			return status;
		}
		// articleInput.jsp select value 로 받은 "1-JAVA" 형태 스트링을
		// 스플릿해서 categoryId, categoryName 으로 사용
		ArticleVO articleInserting = ArticleVO.builder().title(newArticle.getTitle()).writer(newArticle.getWriter())
														.password(newArticle.getPassword()).content(newArticle.getContent())
														.categoryId(newArticle.getCategoryId()).build();
		articleRepository.insertArticle(articleInserting);
		return articleInserting.getArticleId();
	}

	/**
	 * 사용자 입력 비밀번호가 db 패스워드와 일치하는지 검증
	 * @param inputPassword 유저가 articleDetail 모달창에서 입력한 비밀번호
	 * @param articleId 타겟 게시글
	 * @return 불리언값
	 */
	public boolean isPasswordConfirmed(String inputPassword, Integer articleId){
		String dbPassword = articleRepository.selectArticleDetail(articleId).getPassword();
		if (!dbPassword.equals(inputPassword)){
			return false;
		}
		return true;
	}

	/**
	 * 게시글을 삭제해주는 서비스컴포넌트
	 * 관련 파일, 댓글들도 모두 삭제
	 * @param articleId
	 */
	public void deleteArticle(Integer articleId){
		commentRepository.deleteComments(articleId);
		fileRepository.deleteFiles(articleId);
		articleRepository.deleteArticle(articleId);
	}

	public void updateArticle(ArticleVO userInputArticle, Integer articleId){
		ArticleVO insertingArticle = ArticleVO.builder().articleId(articleId).title(userInputArticle.getTitle())
														.writer(userInputArticle.getWriter()).password(userInputArticle.getPassword())
														.content(userInputArticle.getContent()).build();
		articleRepository.updateArticle(insertingArticle);
	}


	private int validateArticleForm(ArticleVO newArticle, String passwordConfirm){
		// 게시판 등록항목 검증
		if (!Objects.equals(newArticle.getPassword(), passwordConfirm)){
			return -1;
		}
		if (newArticle.getWriter().length()> 4 || newArticle.getWriter().length()<3){
			return -2;
		}
		if (StringUtils.isEmpty(newArticle.getCategoryId())){
			return -3;
		}

		if (newArticle.getTitle().length() < 3 || newArticle.getTitle().length() > 100){
			return -4;
		}
		if (newArticle.getContent().length()<4 || newArticle.getContent().length()>2000){
			return -5;
		}
		return 0;
	}
}

