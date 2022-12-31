package com.example.ebrainstudy__springbootbbs.file;

import com.example.ebrainstudy__springbootbbs.file.FileVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface FileMapperInterface {
	public void insertFile(@Param("newFile") FileVO newFile);
	public List<FileVO> selectFiles(int articleId);
	public FileVO selectForDownload(String fileUuid);
}
