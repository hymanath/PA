package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileVO extends ResultStatus implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<String> fileContentType = new ArrayList<String>();
	private List<String> fileName = new ArrayList<String>();
	private List<String> fileTitle;
	private List<String> fileDescription;
	private Long fileTypeId,gallaryId;
	private Long fileId;
	private Long problemFileId;
	private List<String> filePath;
	private String file,gallaryName,gallaryDescription,gallaryCreatedDate;
	private String title,gallaryUpdatedDate,name,path;
	private String description;
	private String pathOfFile;
	private String fileName1;
	private String fileTitle1;
	private String fileDescription1;
	private String filePath1;
	private String problem;
	private String scope;
	private Date existingFrom;
	private Date identifiedOn;
	public String getFileName1() {
		return fileName1;
	}

	public void setFileName1(String fileName1) {
		this.fileName1 = fileName1;
	}

	public String getFileTitle1() {
		return fileTitle1;
	}

	public void setFileTitle1(String fileTitle1) {
		this.fileTitle1 = fileTitle1;
	}

	public String getFileDescription1() {
		return fileDescription1;
	}

	public void setFileDescription1(String fileDescription1) {
		this.fileDescription1 = fileDescription1;
	}

	public String getFilePath1() {
		return filePath1;
	}

	public void setFilePath1(String filePath1) {
		this.filePath1 = filePath1;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public Date getExistingFrom() {
		return existingFrom;
	}

	public void setExistingFrom(Date existingFrom) {
		this.existingFrom = existingFrom;
	}

	public Date getIdentifiedOn() {
		return identifiedOn;
	}

	public void setIdentifiedOn(Date identifiedOn) {
		this.identifiedOn = identifiedOn;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	private String lastName;
	private String fullName;

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

	public Long getGallaryId() {
		return gallaryId;
	}

	public String getGallaryName() {
		return gallaryName;
	}

	public String getGallaryDescription() {
		return gallaryDescription;
	}

	public String getGallaryCreatedDate() {
		return gallaryCreatedDate;
	}

	public String getGallaryUpdatedDate() {
		return gallaryUpdatedDate;
	}

	public String getName() {
		return name;
	}

	public void setGallaryId(Long gallaryId) {
		this.gallaryId = gallaryId;
	}

	public void setGallaryName(String gallaryName) {
		this.gallaryName = gallaryName;
	}

	public void setGallaryDescription(String gallaryDescription) {
		this.gallaryDescription = gallaryDescription;
	}

	public void setGallaryCreatedDate(String gallaryCreatedDate) {
		this.gallaryCreatedDate = gallaryCreatedDate;
	}

	public void setGallaryUpdatedDate(String gallaryUpdatedDate) {
		this.gallaryUpdatedDate = gallaryUpdatedDate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	
}
