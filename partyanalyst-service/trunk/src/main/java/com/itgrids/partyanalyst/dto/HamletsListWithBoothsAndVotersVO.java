package com.itgrids.partyanalyst.dto;

import java.util.List;

public class HamletsListWithBoothsAndVotersVO extends ResultStatus {

	private List<HamletBoothsAndVotersVO>  hamletsListWithBoothsAndVoters;

	public List<HamletBoothsAndVotersVO> getHamletsListWithBoothsAndVoters() {
		return hamletsListWithBoothsAndVoters;
	}

	public void setHamletsListWithBoothsAndVoters(
			List<HamletBoothsAndVotersVO> hamletsListWithBoothsAndVoters) {
		this.hamletsListWithBoothsAndVoters = hamletsListWithBoothsAndVoters;
	}
}
