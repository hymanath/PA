package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class KeywordsVO implements Serializable{

	private Long id;
	private List<String> keywordsList;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<String> getKeywordsList() {
		return keywordsList;
	}
	public void setKeywordsList(List<String> keywordsList) {
		this.keywordsList = keywordsList;
	}
	
	
}
