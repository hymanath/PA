package com.itgrids.partyanalyst.dto;

import java.util.List;

public class SearchListVO {

	 private List<CandidateVO> candidateVOList;
	 private List<CadreInfo> cadreInfo;
	 private Long totalSearchCount;
	 
	 
	public List<CandidateVO> getCandidateVOList() {
		return candidateVOList;
	}
	public void setCandidateVOList(List<CandidateVO> candidateVOList) {
		this.candidateVOList = candidateVOList;
	}
	public Long getTotalSearchCount() {
		return totalSearchCount;
	}
	public void setTotalSearchCount(Long totalSearchCount) {
		this.totalSearchCount = totalSearchCount;
	}
	public List<CadreInfo> getCadreInfo() {
		return cadreInfo;
	}
	public void setCadreInfo(List<CadreInfo> cadreInfo) {
		this.cadreInfo = cadreInfo;
	}
}
