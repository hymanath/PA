package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class MeetingsVO implements Serializable{
	
	private Long constituencyId;
	private String constituency;
	private String district;
	private String incharge;
	private String currentDate;
	private int    month;
	private String explanationDate;
	
	private Long   levelId;
	private String level;
	private int    serialNo;
	private String locationName;
	private String superName;
	
	private int villageWardSerialNo = 0;
	private int mandalTownSerialNo = 0;
	   
	private List<MeetingsVO> villageWardList;
	private List<MeetingsVO> mandalTownList;
	
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getIncharge() {
		return incharge;
	}
	public void setIncharge(String incharge) {
		this.incharge = incharge;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getSuperName() {
		return superName;
	}
	public void setSuperName(String superName) {
		this.superName = superName;
	}
	public List<MeetingsVO> getVillageWardList() {
		return villageWardList;
	}
	public void setVillageWardList(List<MeetingsVO> villageWardList) {
		this.villageWardList = villageWardList;
	}
	public List<MeetingsVO> getMandalTownList() {
		return mandalTownList;
	}
	public void setMandalTownList(List<MeetingsVO> mandalTownList) {
		this.mandalTownList = mandalTownList;
	}
	public Long getLevelId() {
		return levelId;
	}
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	public String getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public String getExplanationDate() {
		return explanationDate;
	}
	public void setExplanationDate(String explanationDate) {
		this.explanationDate = explanationDate;
	}
	public int getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}
	public int getVillageWardSerialNo() {
		return villageWardSerialNo;
	}
	public void setVillageWardSerialNo(int villageWardSerialNo) {
		this.villageWardSerialNo = villageWardSerialNo;
	}
	public int getMandalTownSerialNo() {
		return mandalTownSerialNo;
	}
	public void setMandalTownSerialNo(int mandalTownSerialNo) {
		this.mandalTownSerialNo = mandalTownSerialNo;
	}
	
}
