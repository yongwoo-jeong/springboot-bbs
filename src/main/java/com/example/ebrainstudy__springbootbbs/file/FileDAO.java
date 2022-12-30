package com.example.ebrainstudy__springbootbbs.file;

import com.example.ebrainstudy__springbootbbs.batisMapper.FileMapperInterface;
import com.example.ebrainstudy__springbootbbs.batisMapper.MapperMaker;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileDAO {
	FileMapperInterface mapper;
	@Autowired
	public FileDAO(MapperMaker mapperMaker){
		this.mapper = mapperMaker.getFileMapper();
	}
	public void insertNewFile(FileVO newFile){
		mapper.insertFile(newFile);
	}
	public List<FileVO> getFiles(int articleId){
		return mapper.selectFiles(articleId);
	}
}
