package com.example.ebrainstudy__springbootbbs.pageHandler;

import com.example.ebrainstudy__springbootbbs.article.ArticleDAO;
import com.example.ebrainstudy__springbootbbs.article.ArticleVO;
import com.example.ebrainstudy__springbootbbs.article.SearchConditionVO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import utils.FindCategoryNameId;
import utils.IsEmpty;

/**
 * index 페이지에서 전체 게시글 혹은 검색 조건에 따른
 * 게시글을 모델에서 뷰로 전달하는 컨트롤러
 */
@RequiredArgsConstructor
public class IndexPageHandler implements PageCommandHandler {
	// DB 데이터 송수신을 위한 DAO 객체 주입을 위한 필드
	private final ArticleDAO articleDAO;
	// 쿼리스트링으로 가져온 검색조건 데이터 오브젝트
	private SearchConditionVO searchCondition;

	/**
	 * "/" 인덱스 페이지 핸들러
	 * @param req 컨트롤러에서 전달된 HttpServletRequest
	 * @param res 컨트롤러에서 전달된 HttpServletResponse
	 */
	@Override
	public void process(HttpServletRequest req, HttpServletResponse res){
		// 매퍼에 전달하기 위한 MAP
		Map<String, Object> searchConditionMap = new HashMap<>();
		searchConditionMap.put("keyword", searchCondition.getKeyword());
		// 현재 페이지 정보 (초기값 1)
		int currentPage = searchCondition.getCurrentPage();
		// 매퍼에 들어갈 SELECT LIMIT 오프셋
		// 검색된 게시글
		List<ArticleVO> searchedArticles = articleDAO.searchArticles(currentPage-1,searchConditionMap);
		// 검색조건 유지를 위한 쿼리스트링
		String SearchQuerystring = getSearchQuerystring();
		// 검색된 게시글 수
		int articlesCount = articleDAO.getCountArticles();
		req.setAttribute("articles", searchedArticles);
		req.setAttribute("articlesCount",articlesCount);
		req.setAttribute("currentPage",currentPage);
		req.setAttribute("queryString",SearchQuerystring);
	}

	/**
	 * 컨트롤러에서 설정될 검색조건
	 * @param searchCondition SearchConditionVO
	 */
	public void setSearchCondition(SearchConditionVO searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchQuerystring(){
		StringBuilder querystring = new StringBuilder("");
		String keyword = "";
		String startDate = "";
		String endDate = "";
		if (!IsEmpty.main(searchCondition.getKeyword())){
			keyword = searchCondition.getKeyword();
		}
		if (!IsEmpty.main(searchCondition.getStartDate())){
			startDate = searchCondition.getStartDate();
		}
		if (!IsEmpty.main(searchCondition.getEndDate())){
			endDate = searchCondition.getEndDate();
		}
		querystring.append("?category="+searchCondition.getCategory());
		querystring.append("&keyword="+keyword);
		querystring.append("&startDate="+startDate);
		querystring.append("&endDate="+endDate);
		querystring.append("&currentPage=");
		return querystring.toString();
	}
}
