package com.itgrids.partyanalyst.dto;

import java.util.List;


public class GenderAgeWiseVotersVO extends ResultStatus {

	private List<VoterAgeRangeVO> voterAgeRangeVOList;

	public List<VoterAgeRangeVO> getVoterAgeRangeVOList() {
		return voterAgeRangeVOList;
	}

	public void setVoterAgeRangeVOList(List<VoterAgeRangeVO> voterAgeRangeVOList) {
		this.voterAgeRangeVOList = voterAgeRangeVOList;
	}

}
