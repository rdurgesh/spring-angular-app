package com.spring.angular.app.controller;

import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM_VALUE;
import static org.springframework.http.MediaType.parseMediaType;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.angular.app.domain.FileStore;
import com.spring.angular.app.service.FileStoreService;

@RestController
@RequestMapping("/api/rest/files")
public class FileController {

	private final FileStoreService fileStoreService;

	public FileController(FileStoreService fileStoreService) {
		this.fileStoreService = fileStoreService;
	}

	@PostMapping
	public void storeFile(@RequestBody MultipartFile multipartFile) {
		fileStoreService.storeFile(multipartFile);
	}

	@GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<FileStore>> getAllFiles() {
		List<FileStore> fileStores = fileStoreService.getAllFiles();
		return new ResponseEntity<List<FileStore>>(fileStores, OK);
	}

	@GetMapping(value = "/{param}", produces = APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<FileStore>> searchFile(@PathVariable String param) {
		List<FileStore> fileStores = fileStoreService.searchFileWithName(param);
		return ResponseEntity.ok(fileStores);
	}

	@GetMapping(value = "/file/{fileName:.+}", produces = APPLICATION_OCTET_STREAM_VALUE)
	public void downloadFile(@PathVariable String fileName, HttpServletResponse response) throws IOException {
		FileStore fileStore = fileStoreService.downloadFile(fileName);
		response.setContentType(APPLICATION_OCTET_STREAM_VALUE);
		response.setHeader(CONTENT_DISPOSITION, String.format("attachment; filename=\"" + fileStore.getFileName() + "\""));
		InputStream resource = new ByteArrayInputStream(fileStore.getFile());
		StreamUtils.copy(resource, response.getOutputStream());
	}

	@DeleteMapping(value = "/{fileId}")
	public void deleteFile(@PathVariable Long fileId) {
		fileStoreService.deleteFile(fileId);
	}
}
