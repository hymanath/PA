package com.itgrids.partyanalyst.dto;

import java.util.List;


public class GenderAgeWiseVotersVO extends ResultStatus {

	private List<Long> maleVotersAgeWise;
	private List<Long> femaleVotersAgeWise;
	private List<Long> totalVotersAgeWise;
	public List<Long> getMaleVotersAgeWise() {
		return maleVotersAgeWise;
	}
	public void setMaleVotersAgeWise(List<Long> maleVotersAgeWise) {
		this.maleVotersAgeWise = maleVotersAgeWise;
	}
	public List<Long> getFemaleVotersAgeWise() {
		return femaleVotersAgeWise;
	}
	public void setFemaleVotersAgeWise(List<Long> femaleVotersAgeWise) {
		this.femaleVotersAgeWise = femaleVotersAgeWise;
	}
	public List<Long> getTotalVotersAgeWise() {
		return totalVotersAgeWise;
	}
	public void setTotalVotersAgeWise(List<Long> totalVotersAgeWise) {
		this.totalVotersAgeWise = totalVotersAgeWise;
	}
}
