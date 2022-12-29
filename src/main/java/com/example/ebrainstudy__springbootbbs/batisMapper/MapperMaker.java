package com.example.ebrainstudy__springbootbbs.batisMapper;

import com.example.ebrainstudy__springbootbbs.logger.MyLogger;
import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 아티클, 코멘트, 파일 매퍼를 만들어서 반환해주는 클래스
 */
public class MapperMaker {
	MyLogger logger = MyLogger.getLogger();
	String className = MyLogger.getClassName();
	private SqlSession session;
	/**
	 * 세션팩토리 로드 메서드
	 */
	// 왜 Autowired 를 쓸 수 없는지? SqlSession 이 component 가 아니라 빈 형태로 존재할수없어서?
	// 외부에서 의존성을 주입받지 않기때문에?
	private void SetSession(){
		SqlSessionFactory sqlSessionFactory = null;
		String resource = "mybatis-config.xml";
		try{
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession(true);
		} catch (IOException e) {
			logger.severe(className);
			logger.severe(String.valueOf(e));
		}
}
	public ArticleMapperInterface getArticleMapper(){
		SetSession();
		ArticleMapperInterface mapper = session.getMapper(ArticleMapperInterface.class);
		return mapper;
	}

	public FileMapperInterface getFileMapper(){
		SetSession();
		FileMapperInterface mapper = session.getMapper(FileMapperInterface.class);
		return mapper;
	}

	public void close(){
		session.close();
	}


}



