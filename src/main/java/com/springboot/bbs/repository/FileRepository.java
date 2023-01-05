package com.springboot.bbs.repository;

import com.springboot.bbs.vo.FileVO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

/**
 * 파일 레포지토리
 */
@Repository
@RequiredArgsConstructor
public class FileRepository {

	/**
	 * 매퍼네임
	 */
	private final String mapperName = "com.springboot.bbs.repository.FileRepository";

	/**
	 * sql 세션
	 */
	private final SqlSession session;

	/**
	 * 새 파일 삽입 INSERT INTO file
	 * @param fileVO 파일객체
	 */
	public void insertFile(FileVO fileVO){
		session.insert(mapperName+".insertFile", fileVO);
	}

	/**
	 * 게시글에 달린 파일 리스트 SELECT
	 * @param articleId 게시글 id
	 * @return 파일 리스트
	 */
	public List<FileVO> selectFiles(Integer articleId){
		return session.selectList(mapperName+".selectFiles", articleId);
	}

	/**
	 * 다운로드 스트림 제공을 위한 파일 SELECT
	 * @param fileUuid 파일 id
	 * @return 파일객체
	 */
	public FileVO selectFile(String fileUuid){
		return session.selectOne(mapperName+".selectForDownload", fileUuid);
	}

	/**
	 * 게시글 관련 파일들 삭제
	 * @param articleId 게시글 id
	 */
	public void deleteFiles(Integer articleId){
		session.delete(mapperName+".deleteFiles", articleId);
	}

	/**
	 * 게시글 수정시 필요한 개별파일 삭제
	 * @param fileUuid 파일 uuid (PK)
	 */
	public void deleteFile(String fileUuid){
		session.delete(mapperName+".deleteFile", fileUuid);
	}
}
