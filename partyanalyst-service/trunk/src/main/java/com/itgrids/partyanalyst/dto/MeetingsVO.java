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
	private Long yesCount = 0l;
	private Long noCount = 0l;
	private Long mayBeCount = 0l;
	private Long notUpDatedCount = 0l;
	   
	private List<MeetingsVO> villageWardList;
	private List<MeetingsVO> mandalTownList;
	private List<MeetingsVO> yearWiseMeetingsCount;
	private Long id;
	private Long year=0l;
	private String status;
	private Long count;
	private Long NoOfMonth=0l;
	private String monthName;
	private Long total = 0l;
	public Long getYesCount() {
		return yesCount;
	}
	public void setYesCount(Long yesCount) {
		this.yesCount = yesCount;
	}
	public Long getNoCount() {
		return noCount;
	}
	public void setNoCount(Long noCount) {
		this.noCount = noCount;
	}
	public Long getMayBeCount() {
		return mayBeCount;
	}
	public void setMayBeCount(Long mayBeCount) {
		this.mayBeCount = mayBeCount;
	}
	public Long getNotUpDatedCount() {
		return notUpDatedCount;
	}
	public void setNotUpDatedCount(Long notUpDatedCount) {
		this.notUpDatedCount = notUpDatedCount;
	}
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getYear() {
		return year;
	}
	public void setYear(Long year) {
		this.year = year;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public List<MeetingsVO> getYearWiseMeetingsCount() {
		return yearWiseMeetingsCount;
	}
	public void setYearWiseMeetingsCount(List<MeetingsVO> yearWiseMeetingsCount) {
		this.yearWiseMeetingsCount = yearWiseMeetingsCount;
	}
	public Long getNoOfMonth() {
		return NoOfMonth;
	}
	public void setNoOfMonth(Long noOfMonth) {
		NoOfMonth = noOfMonth;
	}
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	
}
