package com.spring.angular.app.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileStoreModel {

	private Long fileId;

	private String fileName;

	private long fileSize;

	public FileStoreModel(Long fileId, String fileName, long fileSize) {
		this.fileId = fileId;
		this.fileName = fileName;
		this.fileSize = fileSize;
	}
}
