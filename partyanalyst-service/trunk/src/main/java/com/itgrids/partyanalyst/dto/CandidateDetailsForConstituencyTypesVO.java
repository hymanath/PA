package com.itgrids.partyanalyst.dto;

import java.util.List;

public class CandidateDetailsForConstituencyTypesVO {
	
	private CandidateInfoForConstituencyVO parliamentCandidateInfo;
	private List<CandidateInfoForConstituencyVO> assemblyCandidateInfo;
	
	
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
}
