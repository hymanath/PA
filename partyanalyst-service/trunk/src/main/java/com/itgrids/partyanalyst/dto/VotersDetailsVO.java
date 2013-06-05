package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class VotersDetailsVO implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	
	private Long maleVotersCountBetween18To25 = 0L;
	private Long femaleVotersCountBetween18To25 = 0L;
	private Long unknownVotersCountBetween18To25 = 0L;	
	private Long maleVotersCountBetween26To35 = 0L;
	private Long femaleVotersCountBetween26To35 = 0L;
	private Long unknownVotersCountBetween26To35 = 0L;	
	private Long maleVotersCountBetween36To45 = 0L;
	private Long femaleVotersCountBetween36To45 = 0L;
	private Long unknownVotersCountBetween36To45 = 0L;	
	private Long maleVotersCountBetween46To60 = 0L;
	private Long femaleVotersCountBetween46To60 = 0L;
	private Long unknownVotersCountBetween46To60 = 0L;	
	private Long maleVotersCountAbove60 = 0L;
	private Long femaleVotersCountAbove60 = 0L;
	private Long unknownVotersCountAbove60 = 0L;
	
	
	private Long totalMaleVotesFor18To25 = 0L;
	private Long totalFemaleVotersFor18To25 = 0L;
	private Long totalUnknownVotersFor18To25 = 0L;
	private Long totalVotersFor18To25 = 0L;
	
	private Long totalMaleVotersFor26To35 = 0L;
	private Long totalFemaleVotersFor26To35 = 0L;
	private Long totalUnknownVotersFor26To35 = 0L;
	private Long totalVotersFor26To35 = 0L;
	
	private Long totalMaleVotersFor36To45 = 0L;
	private Long totalFemaleVotersFor36To45 = 0L;
	private Long totalUnknownVotersFor36To45 = 0L;
	private Long totalVotersFor36To45 = 0L;
	
	private Long totalMaleVotersFor46To60 = 0L;
	private Long totalFemaleVotersFor46To60 = 0L;
	private Long totalUnknownVotersFor46To60 = 0L;
	private Long totalVotersFor46To60 = 0L;
	
	private Long totalMaleVotersForAbove60 = 0L;
	private Long totalFemaleVotersForAbove60 =0L;
	private Long totalUnknownVotersForAbove60 = 0L;
	private Long totalVotersForAbove60 = 0L;
	
	
	private Long totalMaleVoters = 0L;
	private Long totalFemaleVoters = 0L;
	private Long totalUnknownVoters;
	private Long totalVoters = 0L;
	private Long totalAssignedCount;
	

	private Float totalMaleVotersPercent;
	private Float totalFemaleVotersPercent;
	private Float totalUnknownVotersPercent;
	private Float totalVotersPercent;
	
	private String ageRange;
	private String boothName;
	private String panchayatname;
	private String tehsilName;
	
	private String votersPercentFor18To25;
	private String votersPercentFor26To35;
	private String votersPercentFor36To45;
	private String votersPercentFor46To60;
	private String votersPercentForAbove60;
	
	
	private String maleVotersPercentFor18To25;
	private String maleVotersPercentFor26To35;
	private String maleVotersPercentFor36To45;
	private String maleVotersPercentFor46To60;
	private String maleVotersPercentForAbove60;
	
	private String femaleVotersPercentFor18To25;
	private String femaleVotersPercentFor26To35;
	private String femaleVotersPercentFor36To45;
	private String femaleVotersPercentFor46To60;
	private String femaleVotersPercentForAbove60;
	
	private String areaType;
	
	private Long Totalmandals;
	
	private Long TotalPanchayats;
	
	private Long TotalBooths;
	
	private Long noOfLocalBodies;
	
	private List<SelectOptionVO> mandalList;
	private List<SelectOptionVO> panchayatList;
	
	private List<SelectOptionVO> boothsList;
	
	private List<SelectOptionVO> subPanchayatList;
	
	private List<SelectOptionVO> localbodiesList;
	
	private int totalNoOfWards;
	private int totalNoOfHamlets;
	
	private String hamletName;
	private Long userId;
	
	private String localityName;
	 private Long orderNo;
	
	private Long userVoterCategoryId;
	
	private Long userVoterCategoryValueId; 
	
	private String muncipalityType;
	
	
	private String castName;
	private String casteId;
	
	private int totalVotersCount;
	private String categoryName;
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getTotalVotersCount() {
		return totalVotersCount;
	}
	public void setTotalVotersCount(int totalVotersCount) {
		this.totalVotersCount = totalVotersCount;
	}
	public String getCastName() {
		return castName;
	}
	public void setCastName(String castName) {
		this.castName = castName;
	}
	public String getCasteId() {
		return casteId;
	}
	public void setCasteId(String casteId) {
		this.casteId = casteId;
	}
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public String getMuncipalityType() {
		return muncipalityType;
	}
	public void setMuncipalityType(String muncipalityType) {
		this.muncipalityType = muncipalityType;
	}
	private Long id;
	private String name;
	private List<VotersDetailsVO> votersDetailsVOList;
	
	public Long getUserVoterCategoryValueId() {
		return userVoterCategoryValueId;
	}
	public void setUserVoterCategoryValueId(Long userVoterCategoryValueId) {
		this.userVoterCategoryValueId = userVoterCategoryValueId;
	}
	public Long getUserVoterCategoryId() {
		return userVoterCategoryId;
	}
	public void setUserVoterCategoryId(Long userVoterCategoryId) {
		this.userVoterCategoryId = userVoterCategoryId;
	}
	public String getLocalityName() {
		return localityName;
	}
	public void setLocalityName(String localityName) {
		this.localityName = localityName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getHamletName() {
		return hamletName;
	}
	public void setHamletName(String hamletName) {
		this.hamletName = hamletName;
	}
	public int getTotalNoOfHamlets() {
		return totalNoOfHamlets;
	}
	public void setTotalNoOfHamlets(int totalNoOfHamlets) {
		this.totalNoOfHamlets = totalNoOfHamlets;
	}
	public List<SelectOptionVO> getLocalbodiesList() {
		return localbodiesList;
	}
	public void setLocalbodiesList(List<SelectOptionVO> localbodiesList) {
		this.localbodiesList = localbodiesList;
	}
	public List<SelectOptionVO> getSubPanchayatList() {
		return subPanchayatList;
	}
	public void setSubPanchayatList(List<SelectOptionVO> subPanchayatList) {
		this.subPanchayatList = subPanchayatList;
	}
	public Long getMaleVotersCountBetween18To25() {
		return maleVotersCountBetween18To25;
	}
	public void setMaleVotersCountBetween18To25(Long maleVotersCountBetween18To25) {
		this.maleVotersCountBetween18To25 = maleVotersCountBetween18To25;
	}
	public Long getFemaleVotersCountBetween18To25() {
		return femaleVotersCountBetween18To25;
	}
	public void setFemaleVotersCountBetween18To25(
			Long femaleVotersCountBetween18To25) {
		this.femaleVotersCountBetween18To25 = femaleVotersCountBetween18To25;
	}
	public Long getUnknownVotersCountBetween18To25() {
		return unknownVotersCountBetween18To25;
	}
	public void setUnknownVotersCountBetween18To25(
			Long unknownVotersCountBetween18To25) {
		this.unknownVotersCountBetween18To25 = unknownVotersCountBetween18To25;
	}
	public Long getMaleVotersCountBetween26To35() {
		return maleVotersCountBetween26To35;
	}
	public void setMaleVotersCountBetween26To35(Long maleVotersCountBetween26To35) {
		this.maleVotersCountBetween26To35 = maleVotersCountBetween26To35;
	}
	public Long getFemaleVotersCountBetween26To35() {
		return femaleVotersCountBetween26To35;
	}
	public void setFemaleVotersCountBetween26To35(
			Long femaleVotersCountBetween26To35) {
		this.femaleVotersCountBetween26To35 = femaleVotersCountBetween26To35;
	}
	public Long getUnknownVotersCountBetween26To35() {
		return unknownVotersCountBetween26To35;
	}
	public void setUnknownVotersCountBetween26To35(
			Long unknownVotersCountBetween26To35) {
		this.unknownVotersCountBetween26To35 = unknownVotersCountBetween26To35;
	}
	public Long getMaleVotersCountBetween36To45() {
		return maleVotersCountBetween36To45;
	}
	public void setMaleVotersCountBetween36To45(Long maleVotersCountBetween36To45) {
		this.maleVotersCountBetween36To45 = maleVotersCountBetween36To45;
	}
	public Long getFemaleVotersCountBetween36To45() {
		return femaleVotersCountBetween36To45;
	}
	public void setFemaleVotersCountBetween36To45(
			Long femaleVotersCountBetween36To45) {
		this.femaleVotersCountBetween36To45 = femaleVotersCountBetween36To45;
	}
	public Long getUnknownVotersCountBetween36To45() {
		return unknownVotersCountBetween36To45;
	}
	public void setUnknownVotersCountBetween36To45(
			Long unknownVotersCountBetween36To45) {
		this.unknownVotersCountBetween36To45 = unknownVotersCountBetween36To45;
	}
	public Long getMaleVotersCountBetween46To60() {
		return maleVotersCountBetween46To60;
	}
	public void setMaleVotersCountBetween46To60(Long maleVotersCountBetween46To60) {
		this.maleVotersCountBetween46To60 = maleVotersCountBetween46To60;
	}
	public Long getFemaleVotersCountBetween46To60() {
		return femaleVotersCountBetween46To60;
	}
	public void setFemaleVotersCountBetween46To60(
			Long femaleVotersCountBetween46To60) {
		this.femaleVotersCountBetween46To60 = femaleVotersCountBetween46To60;
	}
	public Long getUnknownVotersCountBetween46To60() {
		return unknownVotersCountBetween46To60;
	}
	public void setUnknownVotersCountBetween46To60(
			Long unknownVotersCountBetween46To60) {
		this.unknownVotersCountBetween46To60 = unknownVotersCountBetween46To60;
	}
	public Long getMaleVotersCountAbove60() {
		return maleVotersCountAbove60;
	}
	public void setMaleVotersCountAbove60(Long maleVotersCountAbove60) {
		this.maleVotersCountAbove60 = maleVotersCountAbove60;
	}
	public Long getFemaleVotersCountAbove60() {
		return femaleVotersCountAbove60;
	}
	public void setFemaleVotersCountAbove60(Long femaleVotersCountAbove60) {
		this.femaleVotersCountAbove60 = femaleVotersCountAbove60;
	}
	public Long getUnknownVotersCountAbove60() {
		return unknownVotersCountAbove60;
	}
	public void setUnknownVotersCountAbove60(Long unknownVotersCountAbove60) {
		this.unknownVotersCountAbove60 = unknownVotersCountAbove60;
	}
	public Long getTotalMaleVotesFor18To25() {
		return totalMaleVotesFor18To25;
	}
	public void setTotalMaleVotesFor18To25(Long totalMaleVotesFor18To25) {
		this.totalMaleVotesFor18To25 = totalMaleVotesFor18To25;
	}
	
	public Long getTotalVotersFor18To25() {
		return totalVotersFor18To25;
	}
	public void setTotalVotersFor18To25(Long totalVotersFor18To25) {
		this.totalVotersFor18To25 = totalVotersFor18To25;
	}
	public Long getTotalVotersFor26To35() {
		return totalVotersFor26To35;
	}
	public void setTotalVotersFor26To35(Long totalVotersFor26To35) {
		this.totalVotersFor26To35 = totalVotersFor26To35;
	}
	public Long getTotalVotersFor36To45() {
		return totalVotersFor36To45;
	}
	public void setTotalVotersFor36To45(Long totalVotersFor36To45) {
		this.totalVotersFor36To45 = totalVotersFor36To45;
	}
	public Long getTotalVotersFor46To60() {
		return totalVotersFor46To60;
	}
	public void setTotalVotersFor46To60(Long totalVotersFor46To60) {
		this.totalVotersFor46To60 = totalVotersFor46To60;
	}
	public Long getTotalVotersForAbove60() {
		return totalVotersForAbove60;
	}
	public void setTotalVotersForAbove60(Long totalVotersForAbove60) {
		this.totalVotersForAbove60 = totalVotersForAbove60;
	}	
	public Long getTotalFemaleVotersFor18To25() {
		return totalFemaleVotersFor18To25;
	}
	public void setTotalFemaleVotersFor18To25(Long totalFemaleVotersFor18To25) {
		this.totalFemaleVotersFor18To25 = totalFemaleVotersFor18To25;
	}
	public Long getTotalUnknownVotersFor18To25() {
		return totalUnknownVotersFor18To25;
	}
	public void setTotalUnknownVotersFor18To25(Long totalUnknownVotersFor18To25) {
		this.totalUnknownVotersFor18To25 = totalUnknownVotersFor18To25;
	}
	public Long getTotalMaleVotersFor26To35() {
		return totalMaleVotersFor26To35;
	}
	public void setTotalMaleVotersFor26To35(Long totalMaleVotersFor26To35) {
		this.totalMaleVotersFor26To35 = totalMaleVotersFor26To35;
	}
	public Long getTotalFemaleVotersFor26To35() {
		return totalFemaleVotersFor26To35;
	}
	public void setTotalFemaleVotersFor26To35(Long totalFemaleVotersFor26To35) {
		this.totalFemaleVotersFor26To35 = totalFemaleVotersFor26To35;
	}
	public Long getTotalUnknownVotersFor26To35() {
		return totalUnknownVotersFor26To35;
	}
	public void setTotalUnknownVotersFor26To35(Long totalUnknownVotersFor26To35) {
		this.totalUnknownVotersFor26To35 = totalUnknownVotersFor26To35;
	}
	public Long getTotalMaleVotersFor36To45() {
		return totalMaleVotersFor36To45;
	}
	public void setTotalMaleVotersFor36To45(Long totalMaleVotersFor36To45) {
		this.totalMaleVotersFor36To45 = totalMaleVotersFor36To45;
	}
	public Long getTotalFemaleVotersFor36To45() {
		return totalFemaleVotersFor36To45;
	}
	public void setTotalFemaleVotersFor36To45(Long totalFemaleVotersFor36To45) {
		this.totalFemaleVotersFor36To45 = totalFemaleVotersFor36To45;
	}
	public Long getTotalUnknownVotersFor36To45() {
		return totalUnknownVotersFor36To45;
	}
	public void setTotalUnknownVotersFor36To45(Long totalUnknownVotersFor36To45) {
		this.totalUnknownVotersFor36To45 = totalUnknownVotersFor36To45;
	}
	public Long getTotalMaleVotersFor46To60() {
		return totalMaleVotersFor46To60;
	}
	public void setTotalMaleVotersFor46To60(Long totalMaleVotersFor46To60) {
		this.totalMaleVotersFor46To60 = totalMaleVotersFor46To60;
	}
	public Long getTotalFemaleVotersFor46To60() {
		return totalFemaleVotersFor46To60;
	}
	public void setTotalFemaleVotersFor46To60(Long totalFemaleVotersFor46To60) {
		this.totalFemaleVotersFor46To60 = totalFemaleVotersFor46To60;
	}
	public Long getTotalUnknownVotersFor46To60() {
		return totalUnknownVotersFor46To60;
	}
	public void setTotalUnknownVotersFor46To60(Long totalUnknownVotersFor46To60) {
		this.totalUnknownVotersFor46To60 = totalUnknownVotersFor46To60;
	}
	public Long getTotalMaleVotersForAbove60() {
		return totalMaleVotersForAbove60;
	}
	public void setTotalMaleVotersForAbove60(Long totalMaleVotersForAbove60) {
		this.totalMaleVotersForAbove60 = totalMaleVotersForAbove60;
	}
	public Long getTotalFemaleVotersForAbove60() {
		return totalFemaleVotersForAbove60;
	}
	public void setTotalFemaleVotersForAbove60(Long totalFemaleVotersForAbove60) {
		this.totalFemaleVotersForAbove60 = totalFemaleVotersForAbove60;
	}
	public Long getTotalUnknownVotersForAbove60() {
		return totalUnknownVotersForAbove60;
	}
	public void setTotalUnknownVotersForAbove60(Long totalUnknownVotersForAbove60) {
		this.totalUnknownVotersForAbove60 = totalUnknownVotersForAbove60;
	}
	public Long getTotalMaleVoters() {
		return totalMaleVoters;
	}
	public void setTotalMaleVoters(Long totalMaleVoters) {
		this.totalMaleVoters = totalMaleVoters;
	}
	public Long getTotalFemaleVoters() {
		return totalFemaleVoters;
	}
	public void setTotalFemaleVoters(Long totalFemaleVoters) {
		this.totalFemaleVoters = totalFemaleVoters;
	}
	public Long getTotalUnknownVoters() {
		return totalUnknownVoters;
	}
	public void setTotalUnknownVoters(Long totalUnknownVoters) {
		this.totalUnknownVoters = totalUnknownVoters;
	}
	public Float getTotalMaleVotersPercent() {
		return totalMaleVotersPercent;
	}
	public void setTotalMaleVotersPercent(Float totalMaleVotersPercent) {
		this.totalMaleVotersPercent = totalMaleVotersPercent;
	}
	public Float getTotalFemaleVotersPercent() {
		return totalFemaleVotersPercent;
	}
	public void setTotalFemaleVotersPercent(Float totalFemaleVotersPercent) {
		this.totalFemaleVotersPercent = totalFemaleVotersPercent;
	}
	public Float getTotalUnknownVotersPercent() {
		return totalUnknownVotersPercent;
	}
	public void setTotalUnknownVotersPercent(Float totalUnknownVotersPercent) {
		this.totalUnknownVotersPercent = totalUnknownVotersPercent;
	}
	public String getAgeRange() {
		return ageRange;
	}
	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}
	public String getBoothName() {
		return boothName;
	}
	public void setBoothName(String boothName) {
		this.boothName = boothName;
	}
	public Long getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	public Float getTotalVotersPercent() {
		return totalVotersPercent;
	}
	public void setTotalVotersPercent(Float totalVotersPercent) {
		this.totalVotersPercent = totalVotersPercent;
	}
	public String getPanchayatname() {
		return panchayatname;
	}
	public void setPanchayatname(String panchayatname) {
		this.panchayatname = panchayatname;
	}
	public String getTehsilName() {
		return tehsilName;
	}
	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}
	public String getVotersPercentFor18To25() {
		return votersPercentFor18To25;
	}
	public void setVotersPercentFor18To25(String votersPercentFor18To25) {
		this.votersPercentFor18To25 = votersPercentFor18To25;
	}
	public String getVotersPercentFor26To35() {
		return votersPercentFor26To35;
	}
	public void setVotersPercentFor26To35(String votersPercentFor26To35) {
		this.votersPercentFor26To35 = votersPercentFor26To35;
	}
	public String getVotersPercentFor36To45() {
		return votersPercentFor36To45;
	}
	public void setVotersPercentFor36To45(String votersPercentFor36To45) {
		this.votersPercentFor36To45 = votersPercentFor36To45;
	}
	public String getVotersPercentFor46To60() {
		return votersPercentFor46To60;
	}
	public void setVotersPercentFor46To60(String votersPercentFor46To60) {
		this.votersPercentFor46To60 = votersPercentFor46To60;
	}
	public String getVotersPercentForAbove60() {
		return votersPercentForAbove60;
	}
	public void setVotersPercentForAbove60(String votersPercentForAbove60) {
		this.votersPercentForAbove60 = votersPercentForAbove60;
	}
	public String getMaleVotersPercentFor18To25() {
		return maleVotersPercentFor18To25;
	}
	public void setMaleVotersPercentFor18To25(String maleVotersPercentFor18To25) {
		this.maleVotersPercentFor18To25 = maleVotersPercentFor18To25;
	}
	public String getMaleVotersPercentFor26To35() {
		return maleVotersPercentFor26To35;
	}
	public void setMaleVotersPercentFor26To35(String maleVotersPercentFor26To35) {
		this.maleVotersPercentFor26To35 = maleVotersPercentFor26To35;
	}
	public String getMaleVotersPercentFor36To45() {
		return maleVotersPercentFor36To45;
	}
	public void setMaleVotersPercentFor36To45(String maleVotersPercentFor36To45) {
		this.maleVotersPercentFor36To45 = maleVotersPercentFor36To45;
	}
	public String getMaleVotersPercentFor46To60() {
		return maleVotersPercentFor46To60;
	}
	public void setMaleVotersPercentFor46To60(String maleVotersPercentFor46To60) {
		this.maleVotersPercentFor46To60 = maleVotersPercentFor46To60;
	}
	public String getMaleVotersPercentForAbove60() {
		return maleVotersPercentForAbove60;
	}
	public void setMaleVotersPercentForAbove60(String maleVotersPercentForAbove60) {
		this.maleVotersPercentForAbove60 = maleVotersPercentForAbove60;
	}
	public String getFemaleVotersPercentFor18To25() {
		return femaleVotersPercentFor18To25;
	}
	public void setFemaleVotersPercentFor18To25(String femaleVotersPercentFor18To25) {
		this.femaleVotersPercentFor18To25 = femaleVotersPercentFor18To25;
	}
	public String getFemaleVotersPercentFor26To35() {
		return femaleVotersPercentFor26To35;
	}
	public void setFemaleVotersPercentFor26To35(String femaleVotersPercentFor26To35) {
		this.femaleVotersPercentFor26To35 = femaleVotersPercentFor26To35;
	}
	public String getFemaleVotersPercentFor36To45() {
		return femaleVotersPercentFor36To45;
	}
	public void setFemaleVotersPercentFor36To45(String femaleVotersPercentFor36To45) {
		this.femaleVotersPercentFor36To45 = femaleVotersPercentFor36To45;
	}
	public String getFemaleVotersPercentFor46To60() {
		return femaleVotersPercentFor46To60;
	}
	public void setFemaleVotersPercentFor46To60(String femaleVotersPercentFor46To60) {
		this.femaleVotersPercentFor46To60 = femaleVotersPercentFor46To60;
	}
	public String getFemaleVotersPercentForAbove60() {
		return femaleVotersPercentForAbove60;
	}
	public void setFemaleVotersPercentForAbove60(
			String femaleVotersPercentForAbove60) {
		this.femaleVotersPercentForAbove60 = femaleVotersPercentForAbove60;
	}
	
	public Long getTotalmandals() {
		return Totalmandals;
	}
	public void setTotalmandals(Long totalmandals) {
		Totalmandals = totalmandals;
	}


	public Long getTotalPanchayats() {
		return TotalPanchayats;
	}
	public void setTotalPanchayats(Long totalPanchayats) {
		TotalPanchayats = totalPanchayats;
	}
	public Long getTotalBooths() {
		return TotalBooths;
	}
	public void setTotalBooths(Long totalBooths) {
		TotalBooths = totalBooths;
	}
	
	public Long getNoOfLocalBodies() {
		return noOfLocalBodies;
	}
	public void setNoOfLocalBodies(Long noOfLocalBodies) {
		this.noOfLocalBodies = noOfLocalBodies;
	}

	
	public List<SelectOptionVO> getMandalList() {
		return mandalList;
	}
	public void setMandalList(List<SelectOptionVO> mandalList) {
		this.mandalList = mandalList;
	}
	
	public List<SelectOptionVO> getPanchayatList() {
		return panchayatList;
	}
	public void setPanchayatList(List<SelectOptionVO> panchayatList) {
		this.panchayatList = panchayatList;
	}
	
	public List<SelectOptionVO> getBoothsList() {
		return boothsList;
	}
	public void setBoothsList(List<SelectOptionVO> boothsList) {
		this.boothsList = boothsList;
	}
	public int getTotalNoOfWards() {
		return totalNoOfWards;
	}
	public void setTotalNoOfWards(int totalNoOfWards) {
		this.totalNoOfWards = totalNoOfWards;
	}
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
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
	public List<VotersDetailsVO> getVotersDetailsVOList() {
		return votersDetailsVOList;
	}
	public void setVotersDetailsVOList(List<VotersDetailsVO> votersDetailsVOList) {
		this.votersDetailsVOList = votersDetailsVOList;
	}
	public Long getTotalAssignedCount() {
		return totalAssignedCount;
	}
	public void setTotalAssignedCount(Long totalAssignedCount) {
		this.totalAssignedCount = totalAssignedCount;
	}
	
	
	
}
