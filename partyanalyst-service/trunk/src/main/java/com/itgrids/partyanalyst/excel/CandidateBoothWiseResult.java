package com.itgrids.partyanalyst.excel;

import java.util.ArrayList;
import java.util.List;

public class CandidateBoothWiseResult {
	
	private String candidateName;
	private List<BoothResultVO> boothresults = new ArrayList<BoothResultVO>();
	
	public CandidateBoothWiseResult(){
		
	}
	
	public CandidateBoothWiseResult(String candidateName,
			List<BoothResultVO> boothresults) {
		this.candidateName = candidateName;
		this.boothresults = boothresults;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public List<BoothResultVO> getBoothresults() {
		return boothresults;
	}

	public void setBoothresults(List<BoothResultVO> boothresults) {
		this.boothresults = boothresults;
	}	

}
