package com.itgrids.partyanalyst.excel;

public class ReportRowDataMapper {
	private String constituencyName;
	private String noOfCandidates;
	private String status;
	private String exceptionInformation;
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public String getNoOfCandidates() {
		return noOfCandidates;
	}
	public void setNoOfCandidates(String noOfCandidates) {
		this.noOfCandidates = noOfCandidates;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getExceptionInformation() {
		return exceptionInformation;
	}
	public void setExceptionInformation(String exceptionInformation) {
		this.exceptionInformation = exceptionInformation;
	}
}
