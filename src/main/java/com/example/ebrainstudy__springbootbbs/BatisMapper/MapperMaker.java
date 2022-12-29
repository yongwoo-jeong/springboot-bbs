package com.example.ebrainstudy__springbootbbs.BatisMapper;

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
	private SqlSession session;
	/**
	 * 세션팩토리 로드해주는 메서드
	 */
	private void SetSession(){
		SqlSessionFactory sqlSessionFactory = null;
		String resource = "mybatis-config.xml";
		try{
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
}
	public ArticleMapperInterface getArticleMapper(){
		SetSession();
		ArticleMapperInterface mapper = session.getMapper(ArticleMapperInterface.class);
		return mapper;
	}

	public void commit(){
		session.commit();
	}
	public void close(){
		session.close();
	}


}



