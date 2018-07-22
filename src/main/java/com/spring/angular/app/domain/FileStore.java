package com.spring.angular.app.domain;

import static javax.persistence.GenerationType.AUTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "FILESTORE")
public class FileStore {

	@Id
	@Column(name = "FILE_ID")
	@GeneratedValue(strategy = AUTO)
	private Long fileId;

	@Column(name = "FILE_NAME")
	private String fileName;

	@Column(name = "FILE_SIZE")
	private long fileSize;

	@Lob
	@Column(name = "FILE_BLOB")
	private byte[] file;
	
	public FileStore(){}

	public FileStore(String fileName, long fileSize, byte[] file) {
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.file = file;
	}
}
