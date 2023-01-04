package com.springboot.bbs.service;

import com.springboot.bbs.repository.FileRepository;
import com.springboot.bbs.utils.FileUtils;
import com.springboot.bbs.vo.FileVO;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 파일 서비스 컴포넌트
 */
@Service
@RequiredArgsConstructor
public class FileService {

	/**
	 * 파일 저장될 서버경로
	 */
	@Value("${dev.file.localPath}")
	private String FILEPATH;

	/**
	 * 파일레포지토리 생성자
	 */
	private final FileRepository fileRepository;

	/**
	 * 로거
	 */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private FileUtils fileUtils;

	/**
	 * 파일 추가 서비스
	 * @param fileList List of MultipartFile
	 * @param articleId 게시글 id
	 */
	public void insertFileService(List<MultipartFile> fileList, Integer articleId){
		for (MultipartFile file : fileList){
			if (FileUtils.isFileEmpty(file)) {
				continue;
			}
			// 유저가 업로드한 파일명
			String fileNameOriginal = file.getOriginalFilename();
			// 파일명 + 현재시간으로 서버에 저장될 파일명 결정
			String fileNameServer = file.getOriginalFilename().split("\\.")[0]+(new Date()).toString().replace(" ","_");
			String filePath = FILEPATH;
			BigInteger fileSize = BigInteger.valueOf(file.getSize());
			// 파일 확장자명
			String fileExtension = file.getOriginalFilename().split("\\.")[1];
			// DB에 저장될 파일 객체생성
			FileVO newFile = FileVO.builder()
									.nameOnServer(fileNameServer)
									.nameOriginal(fileNameOriginal)
									.articleId(articleId).filePath(filePath)
									.fileSize(fileSize).fileExtension(fileExtension)
									.build();
			// 서버 저장될 파일
			File fileToServer = new File(filePath+fileNameOriginal);
			try {
				file.transferTo(fileToServer);
				fileRepository.insertFile(newFile);
			} catch (IOException e) {
				logger.error(String.valueOf(e));
			}
		}
	}
}
