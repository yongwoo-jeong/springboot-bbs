package com.example.ebrainstudy__springbootbbs.BatisMapper;

import com.example.ebrainstudy__springbootbbs.file.FileVO;
import org.apache.ibatis.annotations.Param;

public interface FileMapperInterface {
	public void insertFile(@Param("newFile") FileVO newFile);
}
