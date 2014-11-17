package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
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
	private List<CadreAmountDetailsVO> infoList;
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
	private int poorCCount;
	private int worstCount;
	
	
	
	
	
	
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
	public int getPoorCCount() {
		return poorCCount;
	}
	public void setPoorCCount(int poorCCount) {
		this.poorCCount = poorCCount;
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
	
	
	
	
	
	
	
}
