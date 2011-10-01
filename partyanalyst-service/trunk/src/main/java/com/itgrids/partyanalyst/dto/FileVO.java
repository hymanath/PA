package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FileVO extends ResultStatus implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<String> fileContentType = new ArrayList<String>();
	private List<String> fileName = new ArrayList<String>();
	private List<String> fileTitle;
	private List<String> fileDescription;
	private Long fileTypeId;
	private Long fileId;
	private Long problemFileId;
	private List<String> filePath;
	private String file;
	private String title;
	private String description;
	private String pathOfFile;

	public void setFile(String file) {
		this.file = file;
	}

	public String getFile() {
		return file;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getPathOfFile() {
		return pathOfFile;
	}

	public void setPathOfFile(String pathOfFile) {
		this.pathOfFile = pathOfFile;
	}
	public List<String> getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(List<String> fileContentType) {
		this.fileContentType = fileContentType;
	}

	public List<String> getFileName() {
		return fileName;
	}

	public void setFileName(List<String> fileName) {
		this.fileName = fileName;
	}

	public List<String> getFileTitle() {
		return fileTitle;
	}

	public void setFileTitle(List<String> fileTitle) {
		this.fileTitle = fileTitle;
	}

	public List<String> getFileDescription() {
		return fileDescription;
	}

	public void setFileDescription(List<String> fileDescription) {
		this.fileDescription = fileDescription;
	}

	public Long getFileTypeId() {
		return fileTypeId;
	}

	public void setFileTypeId(Long fileTypeId) {
		this.fileTypeId = fileTypeId;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public Long getProblemFileId() {
		return problemFileId;
	}

	public void setProblemFileId(Long problemFileId) {
		this.problemFileId = problemFileId;
	}

	public List<String> getFilePath() {
		return filePath;
	}

	public void setFilePath(List<String> filePath) {
		this.filePath = filePath;
	}

}
