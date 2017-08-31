package com.itgrids.partyanalyst.dto;

import java.util.List;

public class CandidateDetailsForConstituencyTypesVO {
	
	private CandidateInfoForConstituencyVO parliamentCandidateInfo;
	private List<CandidateInfoForConstituencyVO> assemblyCandidateInfo;
	private String ispartial;
	private List<CandidateDetailsForConstituencyTypesVO> subList;

	public CandidateInfoForConstituencyVO getParliamentCandidateInfo() {
		return parliamentCandidateInfo;
	}
	public void setParliamentCandidateInfo(
			CandidateInfoForConstituencyVO parliamentCandidateInfo) {
		this.parliamentCandidateInfo = parliamentCandidateInfo;
	}
	public List<CandidateInfoForConstituencyVO> getAssemblyCandidateInfo() {
		return assemblyCandidateInfo;
	}
	public void setAssemblyCandidateInfo(
			List<CandidateInfoForConstituencyVO> assemblyCandidateInfo) {
		this.assemblyCandidateInfo = assemblyCandidateInfo;
	}
	public String getIspartial() {
		return ispartial;
	}
	public void setIspartial(String ispartial) {
		this.ispartial = ispartial;
	}

	public List<CandidateDetailsForConstituencyTypesVO> getSubList() {
		return subList;
	}

	public void setSubList(List<CandidateDetailsForConstituencyTypesVO> subList) {
		this.subList = subList;
	}

}
