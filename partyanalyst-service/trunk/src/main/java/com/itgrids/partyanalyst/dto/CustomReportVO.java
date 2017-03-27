package com.itgrids.partyanalyst.dto;

import java.io.File;
import java.io.Serializable;

public class CustomReportVO implements Serializable {
	private Long id;
	private String name;
	private Long count = 0l;
	private Long submited=0l;
	private Long notSubmited=0l;
	private File file;
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public Long getId() {
		return id;
	}
	public Long getSubmited() {
		return submited;
	}
	public void setSubmited(Long submited) {
		this.submited = submited;
	}
	public Long getNotSubmited() {
		return notSubmited;
	}
	public void setNotSubmited(Long notSubmited) {
		this.notSubmited = notSubmited;
	}
	public String getName() {
		return name;
	}
	public Long getCount() {
		return count;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
	
}
