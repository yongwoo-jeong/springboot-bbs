package com.example.ebrainstudy__springbootbbs.service;

import com.example.ebrainstudy__springbootbbs.article.ArticleDAO;
import com.example.ebrainstudy__springbootbbs.article.ArticleVO;
import com.example.ebrainstudy__springbootbbs.searchCondition.SearchCondition;
import com.example.ebrainstudy__springbootbbs.searchCondition.SearchConditionVO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import utils.FindCategoryNameId;

/**
 * index 페이지에서 전체 게시글 혹은 검색 조건에 따른
 * 게시글을 모델에서 뷰로 전달하는 컨트롤러
 */
@Service
@RequiredArgsConstructor // ArticleDAO 생성자 주입을 위한 롬복 애노테이션
public class IndexPageService implements ServiceInterface {
	@Value("${dev.file.localPath}")
	private String path;

	// DB 데이터 CRUD 위한 DAO 객체 의존성 주입
	private final ArticleDAO articleDAO;
	/**
	 * "/" 인덱스 페이지 핸들러
	 * @param req 컨트롤러에서 전달된 HttpServletRequest
	 * @param res 컨트롤러에서 전달된 HttpServletResponse
	 */
	@Override
	public void process(HttpServletRequest req, HttpServletResponse res, SearchConditionVO searchCondition){
		// 바티스 매퍼에 전달하기 위한 검색조건을 가진 MAP
		// 리팩토링 가능할것같은데 ......
		System.out.println(path);
		Map<String, Object> searchConditionMap = new HashMap<>();
		searchConditionMap.put("keyword", searchCondition.getKeyword());
		searchConditionMap.put("categoryId", new FindCategoryNameId().findCategoryIdFn(searchCondition.getCategory()));
		searchConditionMap.put("startDate", searchCondition.getStartDate());
		searchConditionMap.put("endDate", searchCondition.getEndDate());
		// 현재 페이지 정보 (초기값 1)
		int currentPage = searchCondition.getCurrentPage();
		req.setAttribute("currentPage",currentPage);
		// 바티스 매퍼에 들어갈 SELECT LIMIT 오프셋
		int limitStartOffset = (currentPage-1)*10;
		// 검색된 게시글 수
		int articlesCount = articleDAO.getArticlesCount(searchConditionMap);
		req.setAttribute("articlesCount",articlesCount);
		// 검색된 게시글
		List<ArticleVO> searchedArticles = articleDAO.getSearchedArticles(limitStartOffset,searchConditionMap);
		req.setAttribute("articles", searchedArticles);
		// 검색조건 유지를 위한 쿼리스트링
		String SearchQuerystring = new SearchCondition().makeQuerystring(searchCondition);
		req.setAttribute("queryString",SearchQuerystring);
	}

}
