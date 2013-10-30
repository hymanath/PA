package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class LocationVO implements Serializable{

	
	private static final long serialVersionUID = -4230842988612276745L;
	
	private Long scopeId;
	private Long stateId;
	private Long districtId;
	private Long constituencyId;
	private Long populatedId;
	private List<SelectOptionVO> stateList;
	private List<SelectOptionVO> districtList;
	private List<SelectOptionVO> constituencyList;
	private List<SelectOptionVO> mandalList;
	
	public List<SelectOptionVO> getStateList() {
		return stateList;
	}
	public void setStateList(List<SelectOptionVO> stateList) {
		this.stateList = stateList;
	}
	public List<SelectOptionVO> getDistrictList() {
		return districtList;
	}
	public void setDistrictList(List<SelectOptionVO> districtList) {
		this.districtList = districtList;
	}
	public List<SelectOptionVO> getConstituencyList() {
		return constituencyList;
	}
	public void setConstituencyList(List<SelectOptionVO> constituencyList) {
		this.constituencyList = constituencyList;
	}
	public Long getScopeId() {
		return scopeId;
	}
	public void setScopeId(Long scopeId) {
		this.scopeId = scopeId;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getPopulatedId() {
		return populatedId;
	}
	public void setPopulatedId(Long populatedId) {
		this.populatedId = populatedId;
	}
	public List<SelectOptionVO> getMandalList() {
		return mandalList;
	}
	public void setMandalList(List<SelectOptionVO> mandalList) {
		this.mandalList = mandalList;
	}
	
	
}
