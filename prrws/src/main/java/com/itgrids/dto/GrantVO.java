package com.itgrids.dto;

import java.io.Serializable;

public class GrantVO implements Serializable{
	
	private Long programCode;
	private String programName;
	
	
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
	
	

}
