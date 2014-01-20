package com.itgrids.partyanalyst.dto;

import java.io.File;
import java.io.Serializable;
import java.util.Formatter;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

public class FileSourceVO implements Serializable{

	private Long fileSourceId;
	private Long sourceLangId;
	private Integer newsEdition;
	private Long pageNo;
	private Long newsLength;
	private String completeDesc;
	private File fileImage;
	private String newsDescCheck;
	private String fileImageContentType;
	private String fileImageFileName;
	private List<FileVO> fileVOsList;
	private List<FileSourceVO> sourceFileList;
	private List<SelectOptionVO> sourceList;
	private List<SelectOptionVO> languageList;
	private Long newsFont;
	private Long fileSourceLangId;
	private Long filePathId;
	private Long count;
	private String deleted;
	
	
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getFilePathId() {
		return filePathId;
	}
	public void setFilePathId(Long filePathId) {
		this.filePathId = filePathId;
	}
	public Long getFileSourceLangId() {
		return fileSourceLangId;
	}
	public void setFileSourceLangId(Long fileSourceLangId) {
		this.fileSourceLangId = fileSourceLangId;
	}
	public List<SelectOptionVO> getSourceList() {
		return sourceList;
	}
	public void setSourceList(List<SelectOptionVO> sourceList) {
		this.sourceList = sourceList;
	}
	public List<SelectOptionVO> getLanguageList() {
		return languageList;
	}
	public void setLanguageList(List<SelectOptionVO> languageList) {
		this.languageList = languageList;
	}
	public Long getNewsFont() {
		return newsFont;
	}
	public void setNewsFont(Long newsFont) {
		this.newsFont = newsFont;
	}
	public Long getFileSourceId() {
		return fileSourceId;
	}
	public void setFileSourceId(Long fileSourceId) {
		this.fileSourceId = fileSourceId;
	}
	public Long getSourceLangId() {
		return sourceLangId;
	}
	public void setSourceLangId(Long sourceLangId) {
		this.sourceLangId = sourceLangId;
	}
	
	public Long getPageNo() {
		return pageNo;
	}
	public void setPageNo(Long pageNo) {
		this.pageNo = pageNo;
	}
	public Long getNewsLength() {
		return newsLength;
	}
	public void setNewsLength(Long newsLength) {
		this.newsLength = newsLength;
	}
	public String getCompleteDesc() {
		return completeDesc;
	}
	public void setCompleteDesc(String completeDesc) {
		 this.completeDesc = completeDesc;	
	}
	
	
	public File getFileImage() {
		return fileImage;
	}
	public void setFileImage(File fileImage) {
		this.fileImage = fileImage;
	}
	public String getFileImageContentType() {
		return fileImageContentType;
	}
	public void setFileImageContentType(String fileImageContentType) {
		this.fileImageContentType = fileImageContentType;
	}
	public String getFileImageFileName() {
		return fileImageFileName;
	}
	public void setFileImageFileName(String fileImageFileName) {
		this.fileImageFileName = fileImageFileName;
	}
	
	public String getNewsDescCheck() {
		return newsDescCheck;
	}
	public void setNewsDescCheck(String newsDescCheck) {
		this.newsDescCheck = newsDescCheck;
	}
	public List<FileVO> getFileVOsList() {
		return fileVOsList;
	}
	public void setFileVOsList(List<FileVO> fileVOsList) {
		this.fileVOsList = fileVOsList;
	}
	public Integer getNewsEdition() {
		return newsEdition;
	}
	public void setNewsEdition(Integer newsEdition) {
		this.newsEdition = newsEdition;
	}
	public List<FileSourceVO> getSourceFileList() {
		return sourceFileList;
	}
	public void setSourceFileList(List<FileSourceVO> sourceFileList) {
		this.sourceFileList = sourceFileList;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	public String escapeUnicode(String input) {
		  StringBuilder b = new StringBuilder(input.length());
		  Formatter f = new Formatter(b);
		  for (char c : input.toCharArray()) {
		    if (c < 128) {
		      b.append(c);
		    } else {
		      f.format("\\u%04x", (int) c);
		    }
		  }
		  return b.toString();
		}
}
