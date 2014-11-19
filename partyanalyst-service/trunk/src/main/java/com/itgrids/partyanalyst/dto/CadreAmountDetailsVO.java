package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CadreAmountDetailsVO implements Serializable{
	private Long userId;
	private String userName;
	private Long id;
	private String name;
	private String mobileNo;
	private String constituency;
	private Long constituencyId;
	private Long totalCount = 0l;
	private Long totalAmount = 0l;
	private Long paidAmount = 0l;
	
	private Long difference = 0l;
	private String date;
	private List<CadreAmountDetailsVO> infoList = new ArrayList<CadreAmountDetailsVO>();
	private List<CadreAmountDetailsVO> localbodyList = new ArrayList<CadreAmountDetailsVO>();
	
	private String userType;
	
	private Long totalRecords;
	private Long ttAmount;
	private Long totalPaidAmount;
	private Long totalDifference;
	private Long totalVoters;
	private Long targetCadres;
	private Long registeredCadres;
	private String percentage;
	private String parliament;
	private Long parliamentId;
	private String districtName;
	private Long districtId;
	
	private String colorStatus;
	
	private int okCount;
	private int goodCount;
	private int bestCount;
	private int poorCount;
	private int worstCount;
	private String femalePerc;
	private String malePerc;
	private String totalYouthPerc;
	private String cadrePerc;
	private String toDate;
	
	private Long casteId;
	private String casteName;
	private Long casteCategoryId;
	private String casteCategory;
	
	

	
	public Long getCasteId() {
		return casteId;
	}
	public void setCasteId(Long casteId) {
		this.casteId = casteId;
	}
	public String getCasteName() {
		return casteName;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	public Long getCasteCategoryId() {
		return casteCategoryId;
	}
	public void setCasteCategoryId(Long casteCategoryId) {
		this.casteCategoryId = casteCategoryId;
	}
	public String getCasteCategory() {
		return casteCategory;
	}
	public void setCasteCategory(String casteCategory) {
		this.casteCategory = casteCategory;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public List<CadreAmountDetailsVO> getLocalbodyList() {
		return localbodyList;
	}
	public void setLocalbodyList(List<CadreAmountDetailsVO> localbodyList) {
		this.localbodyList = localbodyList;
	}
	public int getOkCount() {
		return okCount;
	}
	public void setOkCount(int okCount) {
		this.okCount = okCount;
	}
	public int getGoodCount() {
		return goodCount;
	}
	public void setGoodCount(int goodCount) {
		this.goodCount = goodCount;
	}
	public int getBestCount() {
		return bestCount;
	}
	public void setBestCount(int bestCount) {
		this.bestCount = bestCount;
	}
	public int getPoorCount() {
		return poorCount;
	}
	public void setPoorCount(int poorCount) {
		this.poorCount = poorCount;
	}
	public int getWorstCount() {
		return worstCount;
	}
	public void setWorstCount(int worstCount) {
		this.worstCount = worstCount;
	}
	public String getColorStatus() {
		return colorStatus;
	}
	public void setColorStatus(String colorStatus) {
		this.colorStatus = colorStatus;
	}
	public String getParliament() {
		return parliament;
	}
	public void setParliament(String parliament) {
		this.parliament = parliament;
	}
	public Long getParliamentId() {
		return parliamentId;
	}
	public void setParliamentId(Long parliamentId) {
		this.parliamentId = parliamentId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	public Long getTargetCadres() {
		return targetCadres;
	}
	public void setTargetCadres(Long targetCadres) {
		this.targetCadres = targetCadres;
	}
	public Long getRegisteredCadres() {
		return registeredCadres;
	}
	public void setRegisteredCadres(Long registeredCadres) {
		this.registeredCadres = registeredCadres;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}
	public Long getTtAmount() {
		return ttAmount;
	}
	public void setTtAmount(Long ttAmount) {
		this.ttAmount = ttAmount;
	}
	public Long getTotalPaidAmount() {
		return totalPaidAmount;
	}
	public void setTotalPaidAmount(Long totalPaidAmount) {
		this.totalPaidAmount = totalPaidAmount;
	}
	public Long getTotalDifference() {
		return totalDifference;
	}
	public void setTotalDifference(Long totalDifference) {
		this.totalDifference = totalDifference;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Long getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(Long paidAmount) {
		this.paidAmount = paidAmount;
	}
	public Long getDifference() {
		return difference;
	}
	public void setDifference(Long difference) {
		this.difference = difference;
	}
	public List<CadreAmountDetailsVO> getInfoList() {
		return infoList;
	}
	public void setInfoList(List<CadreAmountDetailsVO> infoList) {
		this.infoList = infoList;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getFemalePerc() {
		return femalePerc;
	}
	public void setFemalePerc(String femalePerc) {
		this.femalePerc = femalePerc;
	}
	public String getMalePerc() {
		return malePerc;
	}
	public void setMalePerc(String malePerc) {
		this.malePerc = malePerc;
	}
	public String getTotalYouthPerc() {
		return totalYouthPerc;
	}
	public void setTotalYouthPerc(String totalYouthPerc) {
		this.totalYouthPerc = totalYouthPerc;
	}
	public String getCadrePerc() {
		return cadrePerc;
	}
	public void setCadrePerc(String cadrePerc) {
		this.cadrePerc = cadrePerc;
	}

	
	
}
