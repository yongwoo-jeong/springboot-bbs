package com.example.ebrainstudy__springbootbbs.service;

import com.example.ebrainstudy__springbootbbs.file.FileDAO;
import com.example.ebrainstudy__springbootbbs.file.FileVO;
import com.example.ebrainstudy__springbootbbs.logger.MyLogger;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 파일 URL GET 요청시 다운로드 스트림을 제공하는 서비스 컴포넌트
 */
@Service
public class FileDownloadService {

	/**
	 * 로거
	 */
	MyLogger logger = MyLogger.getLogger();
	/**
	 * 로거가 호출된 클래스네임
	 */
	String className = MyLogger.getClassName();

	/**
	 * 다운로드 할 파일 경로, 이름 등 정보를 가져오는 DAO
	 */
	private final FileDAO fileDAO;
	/**
	 * 파일을 특정할 수 있는 uuid
	 */
	private String fileUuid;

	/**
	 * FileDAO 생성자 의존성 주입을 위한 생성자
	 * @param fileDAO
	 */
	@Autowired
	public FileDownloadService(FileDAO fileDAO){
		this.fileDAO = fileDAO;
	}

	/**
	 * file uuid 를 파라미터로 받아와 등록하는 세터메서드
	 * @param fileUuid fileuuid를 url 파라미터로 가져온다.
	 */
	public void setFileUuid(String fileUuid) {
		this.fileUuid = fileUuid;
	}

	/**
	 * 유저 브라우저에 파일 스트림으로 다운로드를 제공하는 메서드
	 * @param req
	 * @param res
	 */
	public void process(HttpServletRequest req, HttpServletResponse res){
		// DAO를 통해 파일정보를 가져온다
		FileVO targetFile = fileDAO.getDownloadFile(fileUuid);
		// 파일이 저장된 서버상 경로
		String serverPath = targetFile.getFilePath();
		// 유저가 업로드할 때의 파일명
		String fileName = targetFile.getNameOriginal();
		// 이를 토대로 java io 파일 객체를 생성
		File fileToUser = new File(serverPath,fileName);
		// String(유니코드문자열)을 바이트코드로 인코딩
		// 인코딩 과정을 건너뛰면 다운로드 될 파일명이 인코딩 되지 않는다.
		fileName =  new String(fileName.getBytes(StandardCharsets.UTF_8),StandardCharsets.ISO_8859_1);
		try {
			FileInputStream fileInputStream = new FileInputStream((fileToUser));
			res.setContentType("application/octet-stream; charset=utf-8");
			res.setHeader("Content-Disposition", "attachment; filename="+fileName);
			OutputStream out = res.getOutputStream();
			int length;
			byte[] readByte = new byte[fileName.length()];
			while ((length = fileInputStream.read(readByte)) > 0) {
				out.write(readByte, 0, length);
			}
			out.flush();
			out.close();
			fileInputStream.close();
		} catch (IOException e) {
			logger.severe(className+"IOEception 발생");
			logger.severe(String.valueOf(e));
		}
	}
}
