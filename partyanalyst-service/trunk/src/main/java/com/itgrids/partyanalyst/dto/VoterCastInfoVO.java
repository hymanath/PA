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
	
	public VoterCastInfoVO(){
		
	}
	
	public VoterCastInfoVO(Long totalVoters, Long maleVoters,
			Long femaleVoters, List<CastVO> castVOs) {
		this.totalVoters = totalVoters;
		this.maleVoters = maleVoters;
		this.femaleVoters = femaleVoters;
		this.castVOs = castVOs;
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

	
}
