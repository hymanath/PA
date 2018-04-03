package com.itgrids.dto;

import java.io.Serializable;

public class GrantVO implements Serializable{
	
	private Long programCode;
	private String programName;
	private Long subGrantId;
	private String subGrantName;
	
	public Long getProgramCode() {
		return programCode;
	}
	public void setProgramCode(Long programCode) {
		this.programCode = programCode;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public Long getSubGrantId() {
		return subGrantId;
	}
	public void setSubGrantId(Long subGrantId) {
		this.subGrantId = subGrantId;
	}
	public String getSubGrantName() {
		return subGrantName;
	}
	public void setSubGrantName(String subGrantName) {
		this.subGrantName = subGrantName;
	}
	
	

}
