package com.example.ebrainstudy__springbootbbs.controller;

import com.example.ebrainstudy__springbootbbs.service.FileDownloadService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FileDownloadController {
	private final FileDownloadService fileDownloadService;
	@Autowired
	public FileDownloadController(FileDownloadService fileDownloadService){
		this.fileDownloadService = fileDownloadService;
	}
	@GetMapping("/download")
	public void getFileDownload(HttpServletRequest req, HttpServletResponse res,@RequestParam String fileId){
		fileDownloadService.setFileUuid(fileId);
		fileDownloadService.process(req, res);
	}
}
