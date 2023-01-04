package com.springboot.bbs.dto;

import java.io.File;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FileDTO {
	private String fileName;
	private File targetFile;

}
