package com.itgrids.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RwsClickVO implements Serializable{

	private Long id;
	private String name;
	
	private String mandalName;
	private String constituencyName;
	private String districtCode;
	private String districtName;
	private String sacntionedAmount;
	private String workName;
	private String workId;
	private String sourceCount;
	private String constituencyCode;
	private String mandalCode;
	private String habitationName;
	private String completionDate="";
	private String habitationCode;
	private Long panchayatId;
	private String panchayat;
	
	private String status;
	private String totalCount;
	private String  toatlPorpualtionCovered;
	private String lpcd;
	private String assetType;
	
	private String coverageStatus;
	private String assestCode;
	private String assestName;
	private String assestCost;
	
	private String commssionedDate ="";
	private String targetDate="";
	private String groundingDate="";
	private String adminDate="";
	
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public String getPanchayat() {
		return panchayat;
	}
	public void setPanchayat(String panchayat) {
		this.panchayat = panchayat;
	}
	public String getAssestCode() {
		return assestCode;
	}
	public void setAssestCode(String assestCode) {
		this.assestCode = assestCode;
	}
	public String getAssestName() {
		return assestName;
	}
	public void setAssestName(String assestName) {
		this.assestName = assestName;
	}
	public String getAssestCost() {
		return assestCost;
	}
	public void setAssestCost(String assestCost) {
		this.assestCost = assestCost;
	}
	public String getCoverageStatus() {
		return coverageStatus;
	}
	public void setCoverageStatus(String coverageStatus) {
		this.coverageStatus = coverageStatus;
	}
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public String getLpcd() {
		return lpcd;
	}
	public void setLpcd(String lpcd) {
		this.lpcd = lpcd;
	}
	public String getToatlPorpualtionCovered() {
		return toatlPorpualtionCovered;
	}
	public void setToatlPorpualtionCovered(String toatlPorpualtionCovered) {
		this.toatlPorpualtionCovered = toatlPorpualtionCovered;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	private List<RwsClickVO> rwsClickList = new ArrayList<RwsClickVO>();
	
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
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	
	public String getSacntionedAmount() {
		return sacntionedAmount;
	}
	public void setSacntionedAmount(String sacntionedAmount) {
		this.sacntionedAmount = sacntionedAmount;
	}
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	
	public String getSourceCount() {
		return sourceCount;
	}
	public void setSourceCount(String sourceCount) {
		this.sourceCount = sourceCount;
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
	public String getHabitationName() {
		return habitationName;
	}
	public void setHabitationName(String habitationName) {
		this.habitationName = habitationName;
	}
	public String getCompletionDate() {
		return completionDate;
	}
	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}
	public String getHabitationCode() {
		return habitationCode;
	}
	public void setHabitationCode(String habitationCode) {
		this.habitationCode = habitationCode;
	}
	public List<RwsClickVO> getRwsClickList() {
		return rwsClickList;
	}
	public void setRwsClickList(List<RwsClickVO> rwsClickList) {
		this.rwsClickList = rwsClickList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCommssionedDate() {
		return commssionedDate;
	}
	public void setCommssionedDate(String commssionedDate) {
		this.commssionedDate = commssionedDate;
	}
	public String getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(String targetDate) {
		this.targetDate = targetDate;
	}
	public String getGroundingDate() {
		return groundingDate;
	}
	public void setGroundingDate(String groundingDate) {
		this.groundingDate = groundingDate;
	}
	public String getAdminDate() {
		return adminDate;
	}
	public void setAdminDate(String adminDate) {
		this.adminDate = adminDate;
	}
	
	
}
