package com.example.ebrainstudy__springbootbbs.service;

import com.example.ebrainstudy__springbootbbs.article.ArticleDAO;
import com.example.ebrainstudy__springbootbbs.article.ArticleVO;
import com.example.ebrainstudy__springbootbbs.exception.InputFIeldException;
import com.example.ebrainstudy__springbootbbs.searchCondition.SearchConditionVO;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class InputArticlePageService implements ServiceInterface {
	/**
	 * 게시글을 INSERT 하기 위해 DAO 객체 의존성주입
	 */
	private final ArticleDAO articleDAO;
	@Autowired
	public InputArticlePageService(ArticleDAO articleDAO){
		this.articleDAO=articleDAO;
	};
	/**
	 * article INSERT 될 새 게시글
	 */
	private ArticleVO insertingArticle;
	/**
	 * file INSERT 될 파일목록
	 */
	private List<MultipartFile> fileList;

	/**
	 * 업로드 될 게시글 DTO 세터
	 * 필드에 입력하기 전 각 항목의 제한 검증
	 * @param insertingArticle
	 */
	public void verifyAndSetArticle(ArticleVO insertingArticle) throws InputFIeldException {
		// 카테고리 ID가 정상적으로 들어왔는지 확인
		List<Integer> existCategory = List.of(1,2,3);
		Integer getCategoryId = insertingArticle.getCategoryId();
		if (!existCategory.contains(getCategoryId)){
			throw new InputFIeldException("카테고리는 필수값입니다.");
		}
		if (insertingArticle.getWriter().length() != 3 && insertingArticle.getWriter().length() != 4){
			throw new InputFIeldException("작성자 길이는 3~4글자입니다.");
		}
		if (insertingArticle.getTitle().length() < 4 || insertingArticle.getTitle().length() > 100){
			throw new InputFIeldException("제목 길이는 4글자 이상, 100글자 미만입니다.");
		}
		if (insertingArticle.getContent().length() < 4 || insertingArticle.getContent().length() > 2000){
			throw new InputFIeldException("제목 길이는 4글자 이상, 100글자 미만입니다.");
		}
		this.insertingArticle = insertingArticle;
	}

	/**
	 * 업로드 될 파일 DTO 세터
	 * @param fileList
	 */
	public void setFileList(List<MultipartFile> fileList){
		this.fileList = fileList;
	}
	@Override
	public void process(HttpServletRequest req, HttpServletResponse res, SearchConditionVO searchCondition) throws IOException {
		articleDAO.insertNewArticle(insertingArticle);
		System.out.println("before insert file");
		Integer articleId = insertingArticle.getArticleId();
		System.out.println(articleId);
		for (MultipartFile file : fileList){
			if (file.getOriginalFilename() == null || file.getSize() == 0) {
				continue;
			}
			String nameOnServer = file.getName();
			String nameOriginal = file.getOriginalFilename();
//			String filePath = env.getProperty("dev.localPath.file");
			BigInteger fileSize = BigInteger.valueOf(file.getSize());
			String fileExtension = file.getOriginalFilename().split("\\.")[1];

		}
		res.sendRedirect("/");
	}
}
