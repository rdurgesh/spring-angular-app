package com.spring.angular.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.angular.app.domain.FileStore;

public interface FileStoreRepository extends JpaRepository<FileStore, Long> {
	public FileStore findByFileName(String fileName);
}
