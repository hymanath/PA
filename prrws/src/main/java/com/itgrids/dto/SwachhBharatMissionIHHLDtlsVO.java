package com.itgrids.dto;

import java.util.List;

public class SwachhBharatMissionIHHLDtlsVO {

	private Long id;
	private String locationIdStr;
	private Long target=0l;
	private Long achivement=0l;
	private String percentage="0";
	private Long locationId;
	private String name;
	private Long grounded;
	private Long noTGrounded;
	private Long completed=0l;
	private Long inProgress;
	private String stateCode;
	private String stateName;
	private String districtName;
	private String constName;
	private String mandalName;
	private String districtCode;
	private String constituencyCode;
	private String mandalCode;
	private String fromDate;
	private String toDate;
	
	private List<SwachhBharatMissionIHHLDtlsVO> subList;
	private List<String> list;
	
	private Long districtCount;
	private Long constituencyCount;
	private Long mandalCount;
	
	private String range;
	
	private Long sanctioned;
	private Long excessben;
	private Long notSanctioned;
	private Long notStarted;
	private String sanctionpercentage="0";
	private String notSanctionPercentage="0";
	private String notStaredPercentage="0";
	private String inProgressPerc="0";
	private String completedPerce="0";
	
	
   public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLocationIdStr() {
		return locationIdStr;
	}
	public void setLocationIdStr(String locationIdStr) {
		this.locationIdStr = locationIdStr;
	}
	public Long getTarget() {
		return target;
	}
	public void setTarget(Long target) {
		this.target = target;
	}
	public Long getAchivement() {
		return achivement;
	}
	public void setAchivement(Long achivement) {
		this.achivement = achivement;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
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
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public Long getGrounded() {
		return grounded;
	}
	public void setGrounded(Long grounded) {
		this.grounded = grounded;
	}
	public Long getNoTGrounded() {
		return noTGrounded;
	}
	public void setNoTGrounded(Long noTGrounded) {
		this.noTGrounded = noTGrounded;
	}
	public Long getCompleted() {
		return completed;
	}
	public void setCompleted(Long completed) {
		this.completed = completed;
	}
	public Long getInProgress() {
		return inProgress;
	}
	public void setInProgress(Long inProgress) {
		this.inProgress = inProgress;
	}
	public List<SwachhBharatMissionIHHLDtlsVO> getSubList() {
		return subList;
	}
	public void setSubList(List<SwachhBharatMissionIHHLDtlsVO> subList) {
		this.subList = subList;
	}
	public Long getDistrictCount() {
		return districtCount;
	}
	public void setDistrictCount(Long districtCount) {
		this.districtCount = districtCount;
	}
	public Long getConstituencyCount() {
		return constituencyCount;
	}
	public void setConstituencyCount(Long constituencyCount) {
		this.constituencyCount = constituencyCount;
	}
	public Long getMandalCount() {
		return mandalCount;
	}
	public void setMandalCount(Long mandalCount) {
		this.mandalCount = mandalCount;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getConstName() {
		return constName;
	}
	public void setConstName(String constName) {
		this.constName = constName;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getConstituencyCode() {
		return constituencyCode;
	}
	public void setConstituencyCode(String constituencyCode) {
		this.constituencyCode = constituencyCode;
	}
	public String getMandalCode() {
		return mandalCode;
	}
	public void setMandalCode(String mandalCode) {
		this.mandalCode = mandalCode;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public Long getSanctioned() {
		return sanctioned;
	}
	public void setSanctioned(Long sanctioned) {
		this.sanctioned = sanctioned;
	}
	public Long getExcessben() {
		return excessben;
	}
	public void setExcessben(Long excessben) {
		this.excessben = excessben;
	}
	public Long getNotSanctioned() {
		return notSanctioned;
	}
	public void setNotSanctioned(Long notSanctioned) {
		this.notSanctioned = notSanctioned;
	}
	public Long getNotStarted() {
		return notStarted;
	}
	public void setNotStarted(Long notStarted) {
		this.notStarted = notStarted;
	}
	public String getSanctionpercentage() {
		return sanctionpercentage;
	}
	public void setSanctionpercentage(String sanctionpercentage) {
		this.sanctionpercentage = sanctionpercentage;
	}
	public String getNotSanctionPercentage() {
		return notSanctionPercentage;
	}
	public void setNotSanctionPercentage(String notSanctionPercentage) {
		this.notSanctionPercentage = notSanctionPercentage;
	}
	public String getNotStaredPercentage() {
		return notStaredPercentage;
	}
	public void setNotStaredPercentage(String notStaredPercentage) {
		this.notStaredPercentage = notStaredPercentage;
	}
	public String getInProgressPerc() {
		return inProgressPerc;
	}
	public void setInProgressPerc(String inProgressPerc) {
		this.inProgressPerc = inProgressPerc;
	}
	public String getCompletedPerce() {
		return completedPerce;
	}
	public void setCompletedPerce(String completedPerce) {
		this.completedPerce = completedPerce;
	}
	
	
}
