package com.example.ebrainstudy__springbootbbs.pageHandler;

import com.example.ebrainstudy__springbootbbs.article.ArticleDAO;
import com.example.ebrainstudy__springbootbbs.article.ArticleVO;
import com.example.ebrainstudy__springbootbbs.article.SearchConditionVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import utils.IsEmpty;

/**
 * index 페이지에서 전체 게시글 혹은 검색 조건에 따른
 * 게시글을 모델에서 뷰로 전달하는 컨트롤러
 */
@RequiredArgsConstructor
public class IndexPageHandler implements PageCommandHandler {
	// DB 데이터 송수신을 위한 DAO 객체 주입을 위한 필드
	private final ArticleDAO articleDAO;
	// 쿼리스트링으로 가져온 검색조건
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
		// 현재 페이지 정보 (초기값 1)
		int currentPage = searchCondition.getCurrentPage();
		// 매퍼에 들어갈 SELECT LIMIT 오프셋
		searchConditionMap.put("articleOffset", (currentPage-1)*10);
		// 검색된 게시글
		List<ArticleVO> searchedArticles = articleDAO.searchArticles(searchConditionMap);
		// 검색된 게시글 수
		int articlesCount = articleDAO.getCountArticles();
		req.setAttribute("articles", searchedArticles);
		req.setAttribute("articlesCount",articlesCount);
		req.setAttribute("currentPage",currentPage);
	}

	/**
	 * 컨트롤러에서 설정될 검색조건
	 * @param searchCondition SearchConditionVO
	 */
	public void setSearchCondition(SearchConditionVO searchCondition) {
		this.searchCondition = searchCondition;
	}
	public SearchConditionVO getSearchQuerystring(){
		StringBuilder querystring = null;
		String keyword = "";
		String category = "";
		String startDate = "";
		String endDate = "";
		querystring.append("?currentPage="+searchCondition.getCurrentPage());
		querystring.append("?startDate="+searchCondition.getCurrentPage());

		querystring.append(searchCondition.getKeyword());
		return searchCondition;
	}
}
