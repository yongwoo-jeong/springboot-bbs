package com.example.ebrainstudy__springbootbbs.article;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Component;

@Component
public class ArticleDAO {
	/**
	 * session commit, close 위한 sql 세션 멤버변수
	 */
	static SqlSession session;
	/**
	 * MapperInterface 로드해주는 메서드
	 * @return
	 */
	public MapperInterface loadMapper(){
		MapperInterface mapper = null;
		String resource = "mybatis-config.xml";
		SqlSessionFactory sqlSessionFactory;
		try{
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();
			mapper = session.getMapper(MapperInterface.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mapper;
	}

	/**
	 * index page, 검색 조건 토대로 해당되는 아티클 인스턴스 리스트를 리턴해주는 메서드
	 * @param selectMap 날짜, 키워드, 카테고리, 페이징 정보가 담긴 Map
	 * @return List<ArticleVO>
	 */
	public List<ArticleVO> searchArticles(int currentPage,Map searchCondition) {
		List<ArticleVO> articleVOFromMapper = loadMapper().selectSearchArticles(currentPage,searchCondition);
		return articleVOFromMapper;
	}
	public int getCountArticles(){
		return loadMapper().selectCountArticles();
	}
}
