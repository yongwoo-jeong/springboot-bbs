package com.springboot.bbs.controller;

import com.springboot.bbs.dto.FileDTO;
import com.springboot.bbs.service.FileService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 파일 컨트롤러
 */
@Controller
@RequiredArgsConstructor
public class FileController {

	/**
	 * 파일 서비스 생성자 DI
	 */
	private final FileService fileService;

	/**
	 * 파일 다운로드 url 컨트롤러
	 */
	@GetMapping("/download")
	public void fileDownloadController(@RequestParam String fileId, HttpServletResponse res){
		FileDTO fileDTO = fileService.makeFileByte(fileId);
		String fileName = fileDTO.getFileName();
		File file = fileDTO.getTargetFile();
		res.setHeader("Content-Type", "application/octet-stream");
		res.setHeader("Content-Disposition", "attachment; filename="+fileName);
		try {
			InputStream is = new FileInputStream(file);
			OutputStream os = res.getOutputStream();
			int length;
			byte[] buffer = new byte[(int) file.length()];

			while ( (length = is.read(buffer))!= -1 ){
				os.write(buffer,0,length);
			}
			os.flush();
			os.close();
			is.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
