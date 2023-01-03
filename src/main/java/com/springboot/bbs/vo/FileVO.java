package com.springboot.bbs.vo;

import java.math.BigInteger;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FileVO {
	/**
	 * 서버에 저장되는 파일명
	 */
	@NonNull
	private String nameOnServer;
	/**
	 * 실제 사용자가 등록한 파일명
	 */
	@NonNull
	private String nameOriginal;
	/**
	 * 해당 파일이 등록되는 게시글 ID
	 */
	@NonNull
	private int articleId;
	/**
	 * 서버에 저장된 파일 경로
	 */
	@NonNull
	private String filePath;
	/**
	 * 파일의 UUID. 프라이머리키.
	 */
	private String fileUuid;
	@NonNull
	private BigInteger fileSize;
	private String fileExtension;

}
