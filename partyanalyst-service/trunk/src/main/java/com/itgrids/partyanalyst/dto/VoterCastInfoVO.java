package com.itgrids.partyanalyst.dto;

import java.util.List;

public class VoterCastInfoVO {

	private Long totalVoters = 0L;
	private Long maleVoters = 0L;
	private Long femaleVoters = 0L;
	private List<CastVO> castVOs;
	private List<VoterCastInfoVO> voterCastInfoVOList;
	private VoterCastInfoVO voterCastInfoVO;
	private String mandalName;
	private String castName;
	private int totalCasts = 0;
	private List<SelectOptionVO> castCategoryWiseVotersList;
	private String votesPercent;
	private Long totalCastKnownVoters;
	private Long locationId;
	
	private String casteCategoryName;
	private Long casteStateId;
	private List<SelectOptionVO> partyWiseCastCategoryList;
	
	private List<VoterCastInfoVO> partyWisevoterCastInfoVOList;
	private int partyWiseTotalCasts = 0;
	
	private Long partyWiseAssignedVoters = 0L;
	private Long partyWiseNotAssignedVoters = 0L;
	
	private String partyName;
	
	private Long partyId;
	private Long reportLevelId;
	
	private Long reportLevelValue;
	private Long publicationDateId;
    private Long userId;
    
    private Double castePercentage;
    
    private Long casteAssignedVoters;
    private Long casteNotAssignedVoters;
    
    private Double partyPercentage;
    private Long hamletId;
    private boolean dataPresent;
    private Long id;
    private String name;
    private Long orderNo;
    
    private String muncipalityType;

	
	public String getMuncipalityType() {
		return muncipalityType;
	}

	public void setMuncipalityType(String muncipalityType) {
		this.muncipalityType = muncipalityType;
	}

	public Long getHamletId() {
		return hamletId;
	}

	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}

	public VoterCastInfoVO(){
		
	}
	
	public VoterCastInfoVO(Long totalVoters, Long maleVoters,
			Long femaleVoters, List<CastVO> castVOs) {
		this.totalVoters = totalVoters;
		this.maleVoters = maleVoters;
		this.femaleVoters = femaleVoters;
		this.castVOs = castVOs;
	}
	
	
	public Double getPartyPercentage() {
		return partyPercentage;
	}

	public void setPartyPercentage(Double partyPercentage) {
		this.partyPercentage = partyPercentage;
	}

	public Long getCasteAssignedVoters() {
		return casteAssignedVoters;
	}

	public void setCasteAssignedVoters(Long casteAssignedVoters) {
		this.casteAssignedVoters = casteAssignedVoters;
	}

	public Long getCasteNotAssignedVoters() {
		return casteNotAssignedVoters;
	}

	public void setCasteNotAssignedVoters(Long casteNotAssignedVoters) {
		this.casteNotAssignedVoters = casteNotAssignedVoters;
	}

	public Double getCastePercentage() {
		return castePercentage;
	}

	public void setCastePercentage(Double castePercentage) {
		this.castePercentage = castePercentage;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPublicationDateId() {
		return publicationDateId;
	}

	public void setPublicationDateId(Long publicationDateId) {
		this.publicationDateId = publicationDateId;
	}
	public Long getReportLevelValue() {
		return reportLevelValue;
	}

	public void setReportLevelValue(Long reportLevelValue) {
		this.reportLevelValue = reportLevelValue;
	}


	public Long getReportLevelId() {
		return reportLevelId;
	}

	public void setReportLevelId(Long reportLevelId) {
		this.reportLevelId = reportLevelId;
	}

	public int getTotalCasts() {
		return totalCasts;
	}

	public void setTotalCasts(int totalCasts) {
		this.totalCasts = totalCasts;
	}
	

	public List<VoterCastInfoVO> getVoterCastInfoVOList() {
		return voterCastInfoVOList;
	}

	public void setVoterCastInfoVOList(List<VoterCastInfoVO> voterCastInfoVOList) {
		this.voterCastInfoVOList = voterCastInfoVOList;
	}

	public String getMandalName() {
		return mandalName;
	}

	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	
	public Long getTotalVoters() {
		return totalVoters;
	}
	
	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	
	public Long getMaleVoters() {
		return maleVoters;
	}
	
	public void setMaleVoters(Long maleVoters) {
		this.maleVoters = maleVoters;
	}
	
	public Long getFemaleVoters() {
		return femaleVoters;
	}
	
	public void setFemaleVoters(Long femaleVoters) {
		this.femaleVoters = femaleVoters;
	}
	
	public List<CastVO> getCastVOs() {
		return castVOs;
	}
	
	public void setCastVOs(List<CastVO> castVOs) {
		this.castVOs = castVOs;
	}

	public VoterCastInfoVO getVoterCastInfoVO() {
		return voterCastInfoVO;
	}

	public void setVoterCastInfoVO(VoterCastInfoVO voterCastInfoVO) {
		this.voterCastInfoVO = voterCastInfoVO;
	}

	public String getCastName() {
		return castName;
	}

	public void setCastName(String castName) {
		this.castName = castName;
	}

	public List<SelectOptionVO> getCastCategoryWiseVotersList() {
		return castCategoryWiseVotersList;
	}

	public void setCastCategoryWiseVotersList(
			List<SelectOptionVO> castCategoryWiseVotersList) {
		this.castCategoryWiseVotersList = castCategoryWiseVotersList;
	}

	public String getVotesPercent() {
		return votesPercent;
	}

	public void setVotesPercent(String votesPercent) {
		this.votesPercent = votesPercent;
	}
	public Long getTotalCastKnownVoters() {
		return totalCastKnownVoters;
	}

	public void setTotalCastKnownVoters(Long totalCastKnownVoters) {
		this.totalCastKnownVoters = totalCastKnownVoters;
	}
	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	
	public String getCasteCategoryName() {
		return casteCategoryName;
	}

	public void setCasteCategoryName(String casteCategoryName) {
		this.casteCategoryName = casteCategoryName;
	}

	public Long getCasteStateId() {
		return casteStateId;
	}

	public void setCasteStateId(Long casteStateId) {
		this.casteStateId = casteStateId;
	}
	
	public List<SelectOptionVO> getPartyWiseCastCategoryList() {
		return partyWiseCastCategoryList;
	}

	public void setPartyWiseCastCategoryList(
			List<SelectOptionVO> partyWiseCastCategoryList) {
		this.partyWiseCastCategoryList = partyWiseCastCategoryList;
	}

	


	
	public List<VoterCastInfoVO> getPartyWisevoterCastInfoVOList() {
		return partyWisevoterCastInfoVOList;
	}

	public void setPartyWisevoterCastInfoVOList(
			List<VoterCastInfoVO> partyWisevoterCastInfoVOList) {
		this.partyWisevoterCastInfoVOList = partyWisevoterCastInfoVOList;
	}
	
	public int getPartyWiseTotalCasts() {
		return partyWiseTotalCasts;
	}

	public void setPartyWiseTotalCasts(int partyWiseTotalCasts) {
		this.partyWiseTotalCasts = partyWiseTotalCasts;
	}
	
	public Long getPartyWiseAssignedVoters() {
		return partyWiseAssignedVoters;
	}

	public void setPartyWiseAssignedVoters(Long partyWiseAssignedVoters) {
		this.partyWiseAssignedVoters = partyWiseAssignedVoters;
	}

	public Long getPartyWiseNotAssignedVoters() {
		return partyWiseNotAssignedVoters;
	}

	public void setPartyWiseNotAssignedVoters(Long partyWiseNotAssignedVoters) {
		this.partyWiseNotAssignedVoters = partyWiseNotAssignedVoters;
	}



	
	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public boolean isDataPresent() {
		return dataPresent;
	}

	public void setDataPresent(boolean dataPresent) {
		this.dataPresent = dataPresent;
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

	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	
}
