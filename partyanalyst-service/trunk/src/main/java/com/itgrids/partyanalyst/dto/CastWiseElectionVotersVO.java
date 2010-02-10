package com.itgrids.partyanalyst.dto;

import java.util.List;

public class CastWiseElectionVotersVO extends ResultStatus{

	private List<CastTotalVotersVO> casteVoters;

	public List<CastTotalVotersVO> getCasteVoters() {
		return casteVoters;
	}

	public void setCasteVoters(List<CastTotalVotersVO> casteVoters) {
		this.casteVoters = casteVoters;
	}
}
