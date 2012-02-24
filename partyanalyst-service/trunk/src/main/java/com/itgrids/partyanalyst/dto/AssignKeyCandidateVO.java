package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class AssignKeyCandidateVO implements Serializable{
	private Long keyCandidateId;
	private String description;
	
	public Long getKeyCandidateId() {
		return keyCandidateId;
	}
	public void setKeyCandidateId(Long keyCandidateId) {
		this.keyCandidateId = keyCandidateId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
