package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class VoterCastInfoVO {

	private Long totalVoters;
	private Long maleVoters;
	private Long femaleVoters;
	private List<CastVO> castVOs;
	private List<VoterCastInfoVO> voterCastInfoVOList;
	private VoterCastInfoVO voterCastInfoVO;
	private String mandalName;
	
	private int totalCasts;
		
	


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
	
	
}
