package com.springboot.bbs.service;

import com.springboot.bbs.repository.FileRepository;
import com.springboot.bbs.vo.FileVO;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileService {
	private final FileRepository fileRepository;

	@Value("${dev.file.localPath}")
	private String serverFilePath;

	public void insertFileService(List<MultipartFile> fileList, Integer articleId){
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
				fileRepository.insertFile(newFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
