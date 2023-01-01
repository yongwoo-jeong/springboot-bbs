package com.example.ebrainstudy__springbootbbs.service;

import com.example.ebrainstudy__springbootbbs.article.ArticleDAO;
import com.example.ebrainstudy__springbootbbs.article.ArticleVO;
import com.example.ebrainstudy__springbootbbs.exception.InputFIeldException;
import com.example.ebrainstudy__springbootbbs.file.FileDAO;
import com.example.ebrainstudy__springbootbbs.file.FileVO;
import com.example.ebrainstudy__springbootbbs.logger.MyLogger;
import com.example.ebrainstudy__springbootbbs.searchCondition.SearchConditionVO;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * POST 요청되는 게시글, 파일 검증 후
 * DB INSERT 하는 서비스 컴포넌트
 */
@Service
public class InsertArticleService implements ServiceInterface {
	/**
	 * 로깅을 위한 마이로거 인스턴스 획득
	 */
	MyLogger logger = MyLogger.getLogger();
	/**
	 * 로깅을 위한 현재 클래스 네임 획득
	 */
	String className = MyLogger.getClassName();
	/**
	 * application.property에 설정된 파일 저장 경로
	 */
	@Value("${dev.file.localPath}")
	private String serverFilePath;
	/**
	 * 게시글을 INSERT 하기 위해 DAO 객체 의존성주입
	 */
	private final ArticleDAO articleDAO;
	private final FileDAO fileDAO;
	@Autowired
	public InsertArticleService(ArticleDAO articleDAO,FileDAO fileDAO){
		this.articleDAO=articleDAO;
		this.fileDAO=fileDAO;
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
	 * @param insertingArticle 프론트에서 input form으로 넘겨받은 article 객체 데이터
	 */
	public void verifyAndSetArticle(ArticleVO insertingArticle) throws InputFIeldException {
		// 카테고리 ID가 정상범위로 들어왔는지 확인
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
	 * 파일이 있을때만 설정됨
	 * @param fileList
	 */
	public void setFileList(List<MultipartFile> fileList){
		this.fileList = fileList;
	}

	/**
	 * 검증 후 파일을 file table INSERT 하고
	 * 서버에 남기는 메서드
	 * @param fileList List of MultipartFile
	 * @param articleId File FK 컬럼에 들어갈 게시글 ID
	 */
	public void insertFile(List<MultipartFile> fileList, Integer articleId) {
		// multipartFile 업로드 처리
		for (MultipartFile file : fileList){
			// 파일명없거나 (null) 파일 사이즈가 0일 경우 건너뛰기
			if (file.getOriginalFilename() == null || file.getSize() == 0) {
				continue;
			}
			String fileNameOriginal = file.getOriginalFilename();
			// 파일명 + 현재시간으로 서버에 저장될 파일명 결정
			String fileNameServer = file.getOriginalFilename().split("\\.")[0]+(new Date()).toString().replace(" ","_");
			String filePath = serverFilePath;
			BigInteger fileSize = BigInteger.valueOf(file.getSize());
			// 파일 확장자명
			String fileExtension = file.getOriginalFilename().split("\\.")[1];
			// DB에 저장될 파일 객체생성
			FileVO newFile = FileVO.builder().nameOnServer(fileNameServer).nameOriginal(fileNameOriginal)
					.articleId(articleId).filePath(filePath)
					.fileSize(fileSize).fileExtension(fileExtension)
					.build();
			// 서버 저장될 파일 객체
			File fileToServer = new File(filePath+fileNameOriginal);
			try {
				file.transferTo(fileToServer);
				fileDAO.insertNewFile(newFile);
			} catch (IOException e) {
				logger.severe(className+"InsertArticleService.insertFile Exception");
				logger.severe(String.valueOf(e));
			}
		}
	}
	/**
	 * article table INSERT 하는 메서드
	 * insertFile 메서드를 호출한다.
	 * @param req
	 * @param res
	 * @param searchCondition 검색 조건 유지를 위한 서치컨디션 객체를 파라미터로 받는다
	 */
	@Override
	public void process(HttpServletRequest req, HttpServletResponse res, SearchConditionVO searchCondition){
		articleDAO.insertNewArticle(insertingArticle);
		Integer articleId = insertingArticle.getArticleId();
		insertFile(fileList, articleId);
		try {
			res.sendRedirect("/");
		} catch (IOException e) {
				logger.severe(className+"InsertArticleService.process Exception");
				logger.severe(String.valueOf(e));
			}
		}
}
