package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.itgrids.partyanalyst.model.BaseModel;

public class GeoLevelReportVO extends BaseModel implements Serializable{

	private Long id;
	private String name;
	private List<Long> statusIds = new ArrayList<Long>();
	private Long boardLevelId;
	private List<Long> casteIds = new ArrayList<Long>();;
	private List<Long> casteGrpIds = new ArrayList<Long>();;
	private List<Long> ageRangeIds = new ArrayList<Long>();
	private String gender ;
	private List<Long> positionIds = new ArrayList<Long>();
	private String locationType;
	private List<Long> locationIds = new ArrayList<Long>();
	private String isCasteChkd;
	private String isPositionChkd;
	private String isCasteGrpChkd;
	private String isGenderChkd;
	private String isAgeRngeChkd;
	private Long stateId;
	
	
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Long> getStatusIds() {
		return statusIds;
	}
	public void setStatusIds(List<Long> statusIds) {
		this.statusIds = statusIds;
	}
	public Long getBoardLevelId() {
		return boardLevelId;
	}
	public void setBoardLevelId(Long boardLevelId) {
		this.boardLevelId = boardLevelId;
	}
	
	public List<Long> getCasteIds() {
		return casteIds;
	}
	public void setCasteIds(List<Long> casteIds) {
		this.casteIds = casteIds;
	}
	public List<Long> getCasteGrpIds() {
		return casteGrpIds;
	}
	public void setCasteGrpIds(List<Long> casteGrpIds) {
		this.casteGrpIds = casteGrpIds;
	}
	public List<Long> getAgeRangeIds() {
		return ageRangeIds;
	}
	public void setAgeRangeIds(List<Long> ageRangeIds) {
		this.ageRangeIds = ageRangeIds;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public List<Long> getPositionIds() {
		return positionIds;
	}
	public void setPositionIds(List<Long> positionIds) {
		this.positionIds = positionIds;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	
	public List<Long> getLocationIds() {
		return locationIds;
	}
	public void setLocationIds(List<Long> locationIds) {
		this.locationIds = locationIds;
	}
	public String getIsCasteChkd() {
		return isCasteChkd;
	}
	public void setIsCasteChkd(String isCasteChkd) {
		this.isCasteChkd = isCasteChkd;
	}
	public String getIsPositionChkd() {
		return isPositionChkd;
	}
	public void setIsPositionChkd(String isPositionChkd) {
		this.isPositionChkd = isPositionChkd;
	}
	public String getIsCasteGrpChkd() {
		return isCasteGrpChkd;
	}
	public void setIsCasteGrpChkd(String isCasteGrpChkd) {
		this.isCasteGrpChkd = isCasteGrpChkd;
	}
	public String getIsGenderChkd() {
		return isGenderChkd;
	}
	public void setIsGenderChkd(String isGenderChkd) {
		this.isGenderChkd = isGenderChkd;
	}
	public String getIsAgeRngeChkd() {
		return isAgeRngeChkd;
	}
	public void setIsAgeRngeChkd(String isAgeRngeChkd) {
		this.isAgeRngeChkd = isAgeRngeChkd;
	}
	
	
}
