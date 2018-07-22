package com.spring.angular.app.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.angular.app.domain.FileStore;
import com.spring.angular.app.model.FileStoreModel;
import com.spring.angular.app.repository.FileStoreRepository;
import com.spring.angular.app.service.FileStoreService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileStoreServiceImpl implements FileStoreService {

	private final FileStoreRepository fileStoreRepository;

	public FileStoreServiceImpl(FileStoreRepository fileStoreRepository) {
		this.fileStoreRepository = fileStoreRepository;
	}

	@Override
	public void storeFile(MultipartFile multipartFile) {
		try {
			String fileName = multipartFile.getOriginalFilename();
			long fileSize = multipartFile.getSize();
			byte[] fileBlob = multipartFile.getBytes();

			FileStore existingFile = fileStoreRepository.findByFileName(fileName);
			if (existingFile != null) {
				existingFile.setFile(fileBlob);
				fileStoreRepository.save(existingFile);
			} else {
				FileStore fileStore = new FileStore(fileName, fileSize, fileBlob);
				fileStoreRepository.save(fileStore);
			}
		} catch (IOException ex) {
			log.error(ex.getMessage());
		}
	}

	@Override
	public FileStore getFile(String fileName) {
		return fileStoreRepository.findByFileName(fileName);
	}

	@Override
	public List<FileStore> getAllFiles() {
		return fileStoreRepository.findAll();
	}

	@Override
	public List<FileStore> searchFileWithName(String param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileStore downloadFile(String fileName) {
		FileStore fileStore = fileStoreRepository.findByFileName(fileName);
		return fileStore;
	}

	@Override
	public void deleteFile(Long fileId) {
		fileStoreRepository.deleteById(fileId);
	}
}
