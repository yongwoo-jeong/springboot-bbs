package com.example.ebrainstudy__springbootbbs.controller;

import com.example.ebrainstudy__springbootbbs.service.FileDownloadService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 파일 다운로드 요청을 처리하는 컨트롤러
 */
@Controller
public class FileDownloadController {

	/**
	 * 파일 다운로드 서비스 객체 의존성
	 */
	private final FileDownloadService fileDownloadService;
	@Autowired
	public FileDownloadController(FileDownloadService fileDownloadService){
		this.fileDownloadService = fileDownloadService;
	}

	/**
	 * 파일 uuid를 받아 서비스 객체에 설정하고 처리시키는 메서드
	 * @param req
	 * @param res
	 * @param fileId
	 */
	@GetMapping("/download")
	public void getFileDownload(HttpServletRequest req, HttpServletResponse res,@RequestParam String fileId){
		fileDownloadService.setFileUuid(fileId);
		fileDownloadService.process(req, res);
	}
}
