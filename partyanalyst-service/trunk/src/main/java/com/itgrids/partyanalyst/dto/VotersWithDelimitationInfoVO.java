package com.itgrids.partyanalyst.dto;

import java.util.List;

public class VotersWithDelimitationInfoVO {

	
	private String year;
	private List<VotersInfoForMandalVO> votersInfoForMandalVO; 
	private List<VotersInfoForMandalVO> votersBasicInfoForMandalVO; 
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public List<VotersInfoForMandalVO> getVotersInfoForMandalVO() {
		return votersInfoForMandalVO;
	}
	public void setVotersInfoForMandalVO(
			List<VotersInfoForMandalVO> votersInfoForMandalVO) {
		this.votersInfoForMandalVO = votersInfoForMandalVO;
	}
	public List<VotersInfoForMandalVO> getVotersBasicInfoForMandalVO() {
		return votersBasicInfoForMandalVO;
	}
	public void setVotersBasicInfoForMandalVO(
			List<VotersInfoForMandalVO> votersBasicInfoForMandalVO) {
		this.votersBasicInfoForMandalVO = votersBasicInfoForMandalVO;
	}
	
	
}
