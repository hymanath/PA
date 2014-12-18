package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CadreRegisterInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2883187909007470353L;

	private Long totalCount;
	private Long apCount;
	private Long tgCount;
	private String name;
	private String area;
	private String location;
	private Long percentage;
	private String date;
	private Long id;
	private List<CadreRegisterInfo> infoList = new ArrayList<CadreRegisterInfo>();
	private List<CadreRegisterInfo> allDetailsList = new ArrayList<CadreRegisterInfo>();
	private List<CadreRegisterInfo> cadreRegisterInfoList;
	private String percentStr;
	private String number;
	private String memberShipNo;
	private Long apWebCount;
	private Long tgWebCount;
	private Long apTabCount;
	private Long tgTabCount;
	private String uname;
	private Long amount;
	private Long apOnlineCount;
	private Long tgOnlineCount;
	private Long totalAmount;
	private String tabNo;
	private String fromDate;
	private Long avgTime;
	
	private Long votersCount;
	private Long targetCount;
	
	private String parliament;
	private String state;
	private String district;
	private String status;
	private Long registeredCount = 0L;
	private Long maleVotersCount = 0L;
	private Long femaleVotersCount = 0L;
	private Long maleCadreCount = 0L;
	private Long femaleCadreCount = 0L;
	private String constituency;
	private Long districtId;
	private Long parliamentId;
	private Long ageRangeCadreValues = 0L;
	private Long ageRangeVoterValues = 0L;
	private List<Long> hours = new ArrayList<Long>();
	
	private Long targetCadre;
	private Long totalVoters;
	private String regPercent;
	private String colorStatus;
	private Long overAllRegCount;
	private Long ghmcTabCount;
	private Long ghmcWebCount;
	
	
	
	public Long getOverAllRegCount() {
		return overAllRegCount;
	}

	public void setOverAllRegCount(Long overAllRegCount) {
		this.overAllRegCount = overAllRegCount;
	}

	public String getRegPercent() {
		return regPercent;
	}

	public void setRegPercent(String regPercent) {
		this.regPercent = regPercent;
	}

	public String getColorStatus() {
		return colorStatus;
	}

	public void setColorStatus(String colorStatus) {
		this.colorStatus = colorStatus;
	}

	public Long getTargetCadre() {
		return targetCadre;
	}

	public void setTargetCadre(Long targetCadre) {
		this.targetCadre = targetCadre;
	}

	public Long getTotalVoters() {
		return totalVoters;
	}

	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}

	public List<Long> getHours() {
		return hours;
	}

	public void setHours(List<Long> hours) {
		this.hours = hours;
	}

	public String getParliament() {
		return parliament;
	}

	public void setParliament(String parliament) {
		this.parliament = parliament;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Long getVotersCount() {
		return votersCount;
	}

	public void setVotersCount(Long votersCount) {
		this.votersCount = votersCount;
	}

	public Long getTargetCount() {
		return targetCount;
	}

	public void setTargetCount(Long targetCount) {
		this.targetCount = targetCount;
	}

	public Long getApPartyWebCount() {
		return apPartyWebCount;
	}

	public void setApPartyWebCount(Long apPartyWebCount) {
		this.apPartyWebCount = apPartyWebCount;
	}

	public Long getTgPartyWebCount() {
		return tgPartyWebCount;
	}

	public void setTgPartyWebCount(Long tgPartyWebCount) {
		this.tgPartyWebCount = tgPartyWebCount;
	}

	private boolean slowUser = false;
	private Long apPartyWebCount;
	private Long tgPartyWebCount;
	
	
	
	
	public boolean isSlowUser() {
		return slowUser;
	}

	public void setSlowUser(boolean slowUser) {
		this.slowUser = slowUser;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getTabNo() {
		return tabNo;
	}

	public void setTabNo(String tabNo) {
		this.tabNo = tabNo;
	}

	public Long getApOnlineCount() {
		return apOnlineCount;
	}

	public void setApOnlineCount(Long apOnlineCount) {
		this.apOnlineCount = apOnlineCount;
	}

	public Long getTgOnlineCount() {
		return tgOnlineCount;
	}

	public void setTgOnlineCount(Long tgOnlineCount) {
		this.tgOnlineCount = tgOnlineCount;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public Long getApWebCount() {
		return apWebCount;
	}

	public void setApWebCount(Long apWebCount) {
		this.apWebCount = apWebCount;
	}

	public Long getTgWebCount() {
		return tgWebCount;
	}

	public void setTgWebCount(Long tgWebCount) {
		this.tgWebCount = tgWebCount;
	}

	public Long getApTabCount() {
		return apTabCount;
	}

	public void setApTabCount(Long apTabCount) {
		this.apTabCount = apTabCount;
	}

	public Long getTgTabCount() {
		return tgTabCount;
	}

	public void setTgTabCount(Long tgTabCount) {
		this.tgTabCount = tgTabCount;
	}

	public String getMemberShipNo() {
		return memberShipNo;
	}

	public void setMemberShipNo(String memberShipNo) {
		this.memberShipNo = memberShipNo;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public List<CadreRegisterInfo> getCadreRegisterInfoList() {
		return cadreRegisterInfoList;
	}

	public void setCadreRegisterInfoList(
			List<CadreRegisterInfo> cadreRegisterInfoList) {
		this.cadreRegisterInfoList = cadreRegisterInfoList;
	}

	public List<CadreRegisterInfo> getAllDetailsList() {
		return allDetailsList;
	}

	public void setAllDetailsList(List<CadreRegisterInfo> allDetailsList) {
		this.allDetailsList = allDetailsList;
	}

	public Long getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
	public Long getApCount() {
		return apCount;
	}
	
	public void setApCount(Long apCount) {
		this.apCount = apCount;
	}
	
	public Long getTgCount() {
		return tgCount;
	}
	
	public void setTgCount(Long tgCount) {
		this.tgCount = tgCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getPercentage() {
		return percentage;
	}

	public void setPercentage(Long percentage) {
		this.percentage = percentage;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<CadreRegisterInfo> getInfoList() {
		return infoList;
	}

	public void setInfoList(List<CadreRegisterInfo> infoList) {
		this.infoList = infoList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPercentStr() {
		return percentStr;
	}

	public void setPercentStr(String percentStr) {
		this.percentStr = percentStr;
	}

	public Long getAvgTime() {
		return avgTime;
	}

	public void setAvgTime(Long avgTime) {
		this.avgTime = avgTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getRegisteredCount() {
		return registeredCount;
	}

	public void setRegisteredCount(Long registeredCount) {
		this.registeredCount = registeredCount;
	}

	public Long getMaleVotersCount() {
		return maleVotersCount;
	}

	public void setMaleVotersCount(Long maleVotersCount) {
		this.maleVotersCount = maleVotersCount;
	}

	public Long getFemaleVotersCount() {
		return femaleVotersCount;
	}

	public void setFemaleVotersCount(Long femaleVotersCount) {
		this.femaleVotersCount = femaleVotersCount;
	}

	public Long getMaleCadreCount() {
		return maleCadreCount;
	}

	public void setMaleCadreCount(Long maleCadreCount) {
		this.maleCadreCount = maleCadreCount;
	}

	public Long getFemaleCadreCount() {
		return femaleCadreCount;
	}

	public void setFemaleCadreCount(Long femaleCadreCount) {
		this.femaleCadreCount = femaleCadreCount;
	}

	public String getConstituency() {
		return constituency;
	}

	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Long getParliamentId() {
		return parliamentId;
	}

	public void setParliamentId(Long parliamentId) {
		this.parliamentId = parliamentId;
	}

	public Long getAgeRangeCadreValues() {
		return ageRangeCadreValues;
	}

	public void setAgeRangeCadreValues(Long ageRangeCadreValues) {
		this.ageRangeCadreValues = ageRangeCadreValues;
	}

	public Long getAgeRangeVoterValues() {
		return ageRangeVoterValues;
	}

	public void setAgeRangeVoterValues(Long ageRangeVoterValues) {
		this.ageRangeVoterValues = ageRangeVoterValues;
	}

	public Long getGhmcTabCount() {
		return ghmcTabCount;
	}

	public void setGhmcTabCount(Long ghmcTabCount) {
		this.ghmcTabCount = ghmcTabCount;
	}

	public Long getGhmcWebCount() {
		return ghmcWebCount;
	}

	public void setGhmcWebCount(Long ghmcWebCount) {
		this.ghmcWebCount = ghmcWebCount;
	}

	
}
