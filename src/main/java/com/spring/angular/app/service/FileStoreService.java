package com.spring.angular.app.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.spring.angular.app.domain.FileStore;

public interface FileStoreService {

	public void storeFile(MultipartFile multipartFile);

	public FileStore getFile(String fileName);

	public List<FileStore> getAllFiles();

	public List<FileStore> searchFileWithName(String param);
	
	public FileStore downloadFile(String fileName);
	
	public void deleteFile(Long fileId);
}
