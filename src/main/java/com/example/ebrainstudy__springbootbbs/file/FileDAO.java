package com.example.ebrainstudy__springbootbbs.file;

import com.example.ebrainstudy__springbootbbs.batisMapper.FileMapperInterface;
import com.example.ebrainstudy__springbootbbs.batisMapper.MapperMaker;
import org.springframework.stereotype.Component;

@Component
public class FileDAO {
	public void insertNewFile(FileVO newFile){
		FileMapperInterface mapper = new MapperMaker().getFileMapper();
		mapper.insertFile(newFile);
	}
}
