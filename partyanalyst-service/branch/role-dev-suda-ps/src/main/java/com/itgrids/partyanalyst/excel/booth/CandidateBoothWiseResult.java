package com.itgrids.partyanalyst.excel.booth;

import java.util.ArrayList;
import java.util.List;

public class CandidateBoothWiseResult {
	
	private String candidateName;
	private List<BoothResultExcelVO> boothresults = new ArrayList<BoothResultExcelVO>();
	
	public CandidateBoothWiseResult(){
		
	}
	
	public CandidateBoothWiseResult(String candidateName,
			List<BoothResultExcelVO> boothresults) {
		this.candidateName = candidateName;
		this.boothresults = boothresults;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public List<BoothResultExcelVO> getBoothresults() {
		return boothresults;
	}

	public void setBoothresults(List<BoothResultExcelVO> boothresults) {
		this.boothresults = boothresults;
	}	

}
