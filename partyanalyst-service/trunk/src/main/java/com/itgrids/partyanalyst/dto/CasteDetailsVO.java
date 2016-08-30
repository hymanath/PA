package com.itgrids.partyanalyst.dto;


public class CasteDetailsVO {
	private String castName;
	private Long castStateId = 0l;
	private Long casteId = 0l;
	private Long stateId = 0l;
	private Long casteCategoryGroupId;
	private Long casteCount;
	private String femaleCount;
	private String maleCount;
	
	private String casteCategoryName;
	public String getFemaleCount() {
		return femaleCount;
	}
	public void setFemaleCount(String femaleCount) {
		this.femaleCount = femaleCount;
	}
	public String getMaleCount() {
		return maleCount;
	}
	public void setMaleCount(String maleCount) {
		this.maleCount = maleCount;
	}
	public Long getCasteCount() {
		return casteCount;
	}
	public void setCasteCount(Long casteCount) {
		this.casteCount = casteCount;
	}
	
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
	public String getCasteCategoryName() {
		return casteCategoryName;
	}
	public void setCasteCategoryName(String casteCategoryName) {
		this.casteCategoryName = casteCategoryName;
	}
	
}
