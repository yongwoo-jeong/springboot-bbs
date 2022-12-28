package com.example.ebrainstudy__springbootbbs.pageHandler;

import com.example.ebrainstudy__springbootbbs.article.ArticleDAO;
import com.example.ebrainstudy__springbootbbs.article.ArticleVO;
import com.example.ebrainstudy__springbootbbs.searchCondition.queryStringMaker;
import com.example.ebrainstudy__springbootbbs.searchCondition.SearchConditionVO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.FindCategoryNameId;

/**
 * index 페이지에서 전체 게시글 혹은 검색 조건에 따른
 * 게시글을 모델에서 뷰로 전달하는 컨트롤러
 */
@Service
@RequiredArgsConstructor // ArticleDAO 생성자 주입을 위한 롬복 애노테이션
public class IndexPageHandler implements PageHandlerInterface {
	// DB 데이터 CRUD 위한 DAO 객체 의존성 주입
	private final ArticleDAO articleDAO;
	// 쿼리스트링으로 가져온 검색조건 데이터 오브젝트
	private SearchConditionVO searchCondition;
	/**
	 * 컨트롤러에서 설정될 검색조건 의존성 주입
	 * @param searchCondition SearchConditionVO
	 */
	@Autowired
	public void setSearchCondition(SearchConditionVO searchCondition) {
		this.searchCondition = searchCondition;
	}
	/**
	 * "/" 인덱스 페이지 핸들러
	 * @param req 컨트롤러에서 전달된 HttpServletRequest
	 * @param res 컨트롤러에서 전달된 HttpServletResponse
	 */
	@Override
	public void process(HttpServletRequest req, HttpServletResponse res){
		// 바티스 매퍼에 전달하기 위한 검색조건을 가진 MAP
		// 리팩토링 가능할것같은데 ......
		Map<String, Object> searchConditionMap = new HashMap<>();
		searchConditionMap.put("keyword", searchCondition.getKeyword());
		searchConditionMap.put("categoryId", new FindCategoryNameId().findCategoryIdFn(searchCondition.getCategory()));
		searchConditionMap.put("startDate",searchCondition.getStartDate());
		searchConditionMap.put("endDate",searchCondition.getEndDate());
		// 현재 페이지 정보 (초기값 1)
		int currentPage = searchCondition.getCurrentPage();
		req.setAttribute("currentPage",currentPage);
		// 바티스 매퍼에 들어갈 SELECT LIMIT 오프셋
		int limitStartOffset = (currentPage-1)*10;
		// 검색된 게시글
		List<ArticleVO> searchedArticles = articleDAO.searchArticles(limitStartOffset,searchConditionMap);
		req.setAttribute("articles", searchedArticles);
		// 검색조건 유지를 위한 쿼리스트링
		String SearchQuerystring = queryStringMaker.makeQuerystring(searchCondition);
		req.setAttribute("queryString",SearchQuerystring);
		// 검색된 게시글 수
		int articlesCount = articleDAO.getCountArticles(searchConditionMap);
		req.setAttribute("articlesCount",articlesCount);
	}

}
