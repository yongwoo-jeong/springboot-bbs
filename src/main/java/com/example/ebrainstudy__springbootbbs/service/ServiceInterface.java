package com.example.ebrainstudy__springbootbbs.service;

import com.example.ebrainstudy__springbootbbs.searchCondition.SearchConditionVO;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서비스 인터페이스
 */
public interface ServiceInterface {
	/**
	 * 서비스 인터페이스
	 * @param req
	 * @param res
	 * @param searchCondition 검색 조건 유지를 위한 서치컨디션 객체를 파라미터로 받는다
	 * @throws IOException
	 */
	public void process(HttpServletRequest req, HttpServletResponse res, SearchConditionVO searchCondition) throws IOException;
}
