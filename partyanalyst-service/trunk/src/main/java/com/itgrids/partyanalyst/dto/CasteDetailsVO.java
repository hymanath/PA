package com.itgrids.partyanalyst.dto;

import java.util.List;

public class CasteDetailsVO {
	private String castName;
	private Long castStateId = 0l;
	private Long casteId = 0l;
	private Long stateId = 0l;
	private Long casteCategoryGroupId;
	
	public String getCastName() {
		return castName;
	}
	public void setCastName(String castName) {
		this.castName = castName;
	}
	public Long getCastStateId() {
		return castStateId;
	}
	public void setCastStateId(Long castStateId) {
		this.castStateId = castStateId;
	}
	public Long getCasteId() {
		return casteId;
	}
	public void setCasteId(Long casteId) {
		this.casteId = casteId;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public Long getCasteCategoryGroupId() {
		return casteCategoryGroupId;
	}
	public void setCasteCategoryGroupId(Long casteCategoryGroupId) {
		this.casteCategoryGroupId = casteCategoryGroupId;
	}
	
}
