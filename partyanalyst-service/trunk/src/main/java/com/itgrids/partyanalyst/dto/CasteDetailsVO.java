package com.itgrids.partyanalyst.dto;

import java.util.List;

public class CasteDetailsVO {
	private String castName;
	private Long castStateId;
	private Long casteId;
	private Long stateId;
	private Long casteCategoryGroupId;
	private List<CasteDetailsVO> casteList,casteStateList;
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
	public List<CasteDetailsVO> getCasteList() {
		return casteList;
	}
	public void setCasteList(List<CasteDetailsVO> casteList) {
		this.casteList = casteList;
	}
	public List<CasteDetailsVO> getCasteStateList() {
		return casteStateList;
	}
	public void setCasteStateList(List<CasteDetailsVO> casteStateList) {
		this.casteStateList = casteStateList;
	}
	
	
	
}
