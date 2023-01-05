package com.springboot.bbs.dto;

import java.io.File;
import lombok.Getter;
import lombok.Setter;

/**
 * 파일 컨트롤러-서비스에 필요한 DTO
 */
@Setter
@Getter
public class FileDTO {

	/**
	 * 헤더에 필요한 파일네임
	 */
	private String fileName;

	/**
	 * 인풋스트림 설정에 필요한 파일객체
	 */
	private File targetFile;

}
