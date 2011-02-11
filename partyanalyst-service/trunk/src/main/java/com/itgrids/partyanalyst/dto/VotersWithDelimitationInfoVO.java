package com.itgrids.partyanalyst.dto;

import java.util.List;

public class VotersWithDelimitationInfoVO {

	
	private String year;
	private String type;
	private List<VotersInfoForMandalVO> votersInfoForMandalVO; 
	private List<VotersInfoForMandalVO> votersBasicInfoForMandalVO; 
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
