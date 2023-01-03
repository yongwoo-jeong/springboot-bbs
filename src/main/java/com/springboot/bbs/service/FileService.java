package com.springboot.bbs.service;

import com.springboot.bbs.repository.FileRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileService {
	private FileRepository fileRepository;
	public void insertFileService(List<MultipartFile> fileList){
				
	}
}
