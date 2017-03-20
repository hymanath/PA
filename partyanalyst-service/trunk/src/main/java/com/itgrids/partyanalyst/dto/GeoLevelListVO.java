package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class GeoLevelListVO {
	private Long levelId;
	private String name;
	private Long levelValue;
	private Long  maleCount = 0l;
	private Long  feMaleCount = 0l;
	private Long  total = 0l;
	private List<GeoLevelListVO> boardLvlList  = new ArrayList<GeoLevelListVO>();
	private List<GeoLevelListVO> ageRangeList  = new ArrayList<GeoLevelListVO>();
	private List<GeoLevelListVO> casteList  = new ArrayList<GeoLevelListVO>();
	private List<GeoLevelListVO> casteCagryList  = new ArrayList<GeoLevelListVO>();
	private List<GeoLevelListVO> positionList  = new ArrayList<GeoLevelListVO>();
	private List<GeoLevelListVO> locList  = new ArrayList<GeoLevelListVO>();
	private Long locationId;
	private Long positionId;
	private Long casteCatGrpId;
	private Long casteId;
	private Long boardLvlId;
	private Long ageRangId;
	private Long count;
	private String gender;
	
	public GeoLevelListVO(){}
	public GeoLevelListVO(Long levelId,String name){
		this.levelId = levelId;
		this.name= name;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getPositionId() {
		return positionId;
	}
	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}
	public Long getCasteCatGrpId() {
		return casteCatGrpId;
	}
	public void setCasteCatGrpId(Long casteCatGrpId) {
		this.casteCatGrpId = casteCatGrpId;
	}
	public Long getCasteId() {
		return casteId;
	}
	public void setCasteId(Long casteId) {
		this.casteId = casteId;
	}
	public Long getBoardLvlId() {
		return boardLvlId;
	}
	public void setBoardLvlId(Long boardLvlId) {
		this.boardLvlId = boardLvlId;
	}
	public Long getAgeRangId() {
		return ageRangId;
	}
	public void setAgeRangId(Long ageRangId) {
		this.ageRangId = ageRangId;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getLevelId() {
		return levelId;
	}
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
	public Long getLevelValue() {
		return levelValue;
	}
	public void setLevelValue(Long levelValue) {
		this.levelValue = levelValue;
	}
	public Long getMaleCount() {
		return maleCount;
	}
	public void setMaleCount(Long maleCount) {
		this.maleCount = maleCount;
	}
	public Long getFeMaleCount() {
		return feMaleCount;
	}
	public void setFeMaleCount(Long feMaleCount) {
		this.feMaleCount = feMaleCount;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<GeoLevelListVO> getBoardLvlList() {
		return boardLvlList;
	}
	public void setBoardLvlList(List<GeoLevelListVO> boardLvlList) {
		this.boardLvlList = boardLvlList;
	}
	public List<GeoLevelListVO> getAgeRangeList() {
		return ageRangeList;
	}
	public void setAgeRangeList(List<GeoLevelListVO> ageRangeList) {
		this.ageRangeList = ageRangeList;
	}
	public List<GeoLevelListVO> getCasteList() {
		return casteList;
	}
	public void setCasteList(List<GeoLevelListVO> casteList) {
		this.casteList = casteList;
	}
	public List<GeoLevelListVO> getCasteCagryList() {
		return casteCagryList;
	}
	public void setCasteCagryList(List<GeoLevelListVO> casteCagryList) {
		this.casteCagryList = casteCagryList;
	}
	public List<GeoLevelListVO> getPositionList() {
		return positionList;
	}
	public void setPositionList(List<GeoLevelListVO> positionList) {
		this.positionList = positionList;
	}
	public List<GeoLevelListVO> getLocList() {
		return locList;
	}
	public void setLocList(List<GeoLevelListVO> locList) {
		this.locList = locList;
	}
	

}
