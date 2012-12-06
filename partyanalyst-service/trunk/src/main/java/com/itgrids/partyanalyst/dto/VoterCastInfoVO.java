package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class VoterCastInfoVO {

	private Long totalVoters;
	private Long maleVoters;
	private Long femaleVoters;
	private List<CastVO> castVOs;
	private List<VoterCastInfoVO> castVosList = new ArrayList<VoterCastInfoVO>();
	
	private String mandalName;
		
	

	public VoterCastInfoVO(){
		
	}
	
	public VoterCastInfoVO(Long totalVoters, Long maleVoters,
			Long femaleVoters, List<CastVO> castVOs) {
		this.totalVoters = totalVoters;
		this.maleVoters = maleVoters;
		this.femaleVoters = femaleVoters;
		this.castVOs = castVOs;
	}
	
	public List<VoterCastInfoVO> getCastVosList() {
		return castVosList;
	}

	public void setCastVosList(List<VoterCastInfoVO> castVosList) {
		this.castVosList = castVosList;
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
	
	
}
