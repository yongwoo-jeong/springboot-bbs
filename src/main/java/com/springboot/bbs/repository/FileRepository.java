package com.springboot.bbs.repository;

import com.springboot.bbs.vo.FileVO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FileRepository {
	private final String mapperName = "com.springboot.bbs.repository.FileRepository";
	private final SqlSession session;

	public void insertFile(FileVO fileVO){
		session.insert(mapperName+".insertFile", fileVO);
	}
}
