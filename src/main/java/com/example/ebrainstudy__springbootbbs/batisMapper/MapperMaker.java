package com.example.ebrainstudy__springbootbbs.batisMapper;

import com.example.ebrainstudy__springbootbbs.article.ArticleMapperInterface;
import com.example.ebrainstudy__springbootbbs.comment.CommentMapperInterface;
import com.example.ebrainstudy__springbootbbs.file.FileMapperInterface;
import com.example.ebrainstudy__springbootbbs.logger.MyLogger;
import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * sql 세션을 생성하고
 * 마이바티스 매퍼 인터페스를 반환해주는 클래스
 */
@Component
public class MapperMaker {

	/**
	 * 로깅 위한 로거객체, 클래스네임
	 */
	MyLogger logger = MyLogger.getLogger();
	String className = MyLogger.getClassName();
	/**
	 * SQL 세션 의존성주입
	 */
	private SqlSession session;
	/**
	 * sqlSession 세터
	 */
	@Autowired
	private void SetSession(){
		SqlSessionFactory sqlSessionFactory;
		String resource = "mybatis-config.xml";
		try{
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			// 오토커밋 설정
			session = sqlSessionFactory.openSession(true);
		} catch (IOException e) {
			logger.severe(className);
			logger.severe(String.valueOf(e));
		}
}

	/**
	 * 아티클 매퍼 인터페이스를 반환해주는 메서드
	 * @return ArticleMapperInterface
	 */
	public ArticleMapperInterface getArticleMapper(){
		SetSession();
		ArticleMapperInterface mapper = session.getMapper(ArticleMapperInterface.class);
		return mapper;
	}

	/**
	 * 파일 매퍼 인터페이스를 반환해주는 메서드
	 * @return FileMapperInterface
	 */
	public FileMapperInterface getFileMapper(){
		SetSession();
		FileMapperInterface mapper = session.getMapper(FileMapperInterface.class);
		return mapper;
	}

	public CommentMapperInterface getCommentMapper(){
		SetSession();
		CommentMapperInterface mapper = session.getMapper(CommentMapperInterface.class);
		return mapper;
	}
}



