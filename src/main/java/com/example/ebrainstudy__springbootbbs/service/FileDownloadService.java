package com.example.ebrainstudy__springbootbbs.service;

import com.example.ebrainstudy__springbootbbs.file.FileDAO;
import com.example.ebrainstudy__springbootbbs.file.FileVO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileDownloadService {
	private final FileDAO fileDAO;
	private String fileUuid;
	@Autowired
	public FileDownloadService(FileDAO fileDAO){
		this.fileDAO = fileDAO;
	}
	public void setFileUuid(String fileUuid) {
		this.fileUuid = fileUuid;
	}
	public void process(HttpServletRequest req, HttpServletResponse res){
		FileVO targetFile = fileDAO.getDownloadFile(fileUuid);
		String serverPath = targetFile.getFilePath();
		String fileName = targetFile.getNameOriginal();
		File fileToUser = new File(serverPath,fileName);
		fileName =  new String(fileName.getBytes(StandardCharsets.UTF_8),StandardCharsets.ISO_8859_1);
		try {
			FileInputStream fileInputStream = new FileInputStream((fileToUser));
			res.setContentType("application/octet-stream; charset=utf-8");
			res.setHeader("Content-Disposition", "attachment; filename="+fileName);
			OutputStream out = res.getOutputStream();
			int length;
			byte[] b = new byte[fileName.length()];
			while ((length = fileInputStream.read(b)) > 0) {
				out.write(b, 0, length);
			}
			out.flush();
			out.close();
			fileInputStream.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

//		while ((length = fileInputStream.read(b)) > 0) {
//			out.write(b, 0, length);
//		}
//		out.flush();
//		out.close();
//		fileInputStream.close();

	}
}
