package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class MetaInfoVO extends ResultStatus implements Serializable{

	private static final long serialVersionUID = 825163862249593601L;
	
	private String keywords;
	private String description;
	private Long specialPageId;
	
	public Long getSpecialPageId() {
		return specialPageId;
	}
	public void setSpecialPageId(Long specialPageId) {
		this.specialPageId = specialPageId;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
